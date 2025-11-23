package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.BudgetRequest;
import com.matin_devs.tally.exception.BudgetNotFoundForUserException;
import com.matin_devs.tally.model.Budget;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.repository.BudgetRepository;
import com.matin_devs.tally.repository.ExpenseRepository;
import com.matin_devs.tally.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final ExpenseRepository expenseRepository;

    /**
     * Adds budget to the database
     * @param request Budget Data Transfer Object
     */
    public void addBudget(BudgetRequest request) {
        // validate user ID for sending to budget constructor
        User user = userRepository.getReferenceById(request.getUserId());

        // build budget
        Budget budget = Budget.builder()
                .frequency(request.getFrequency())
                .user(user)
                .build();

        // add to database
        budgetRepository.save(budget);
    }

    /**
     * Get budget by user ID
     * @param userId UUID of User
     * @return Budget instance which is linked to the user
     * @throws BudgetNotFoundForUserException if no budgets are set for user
     */
    public Budget getBudgetByUserId(Long userId) throws BudgetNotFoundForUserException {
        User user = userRepository.getReferenceById(userId);
        return budgetRepository.findByUser(user)
                .orElseThrow(() -> new BudgetNotFoundForUserException(user));
    }

    /**
     * Updates existing budget
     * @param id budget ID for tracking
     * @param request Budget Data Transfer Object
     */
    @Transactional
    public void updateBudgetById(Long id, BudgetRequest request) {
        // get Budget by ID
        Budget budget = budgetRepository.getReferenceById(id);

        // get all expenses and set to new array
        Set<Expense> expenses = budget.getExpenseList();
        Set<Long> requestExpenseList = request.getExpenseIdList();
        for (Long expense : requestExpenseList) {
            expenses.add(expenseRepository.getReferenceById(expense));
        }

        // a user should be able to add to all fields besides which user the budget is set to
        budget.setFrequency(request.getFrequency());
        budget.setExpenseList(expenses);
    }

    /**
     * delete Budget instance using ID
     * @param id for Budget class
     */
    public void deleteBudgetById(Long id) {
        budgetRepository.deleteById(id);
    }
}
