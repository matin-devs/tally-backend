package com.matin_devs.tally.service;

import com.matin_devs.tally.common.TimePeriod;
import com.matin_devs.tally.exception.BudgetNotFoundForUserException;
import com.matin_devs.tally.model.Budget;
import com.matin_devs.tally.model.TransactionCategory;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.repository.BudgetRepository;
import com.matin_devs.tally.repository.TransactionRepository;
import com.matin_devs.tally.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BudgetService {
    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;
    private final TransactionRepository expenseRepository;

    /**
     * Adds budget to the database
     */
    public void addBudget(User user, TimePeriod frequency) {
        // build budget
        Budget budget = Budget.builder()
                .frequency(frequency)
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
    public Budget getBudgetByUserId(UUID userId) throws BudgetNotFoundForUserException {
        User user = userRepository.getReferenceById(userId);
        return budgetRepository.findByUser(user)
                .orElseThrow(() -> new BudgetNotFoundForUserException(user));
    }

    /**
     * Updates existing budget
     * @param id budget ID for tracking
     */
    @Transactional
    public void updateBudgetById(UUID id) {
        // get Budget by ID
        Budget budget = budgetRepository.getReferenceById(id);

        //TODO: Up
    }

    /**
     * delete Budget instance using ID
     * @param id for Budget class
     */
    public void deleteBudgetById(UUID id) {
        budgetRepository.deleteById(id);
    }

    public void updateBudgetCategory(
            @AuthenticationPrincipal User user,
            TransactionCategory category,
            BigDecimal newAmount) {

        Budget userBudget = getBudgetByUserId(user.getId());
        userBudget.getCategoryBudgets().put(category, newAmount);
    }
}
