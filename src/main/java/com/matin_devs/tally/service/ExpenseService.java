package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    // TODO: Return void
    public Expense addExpense(ExpenseRequest request) {

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .amount(request.getAmount())
                .date(request.getDate())
                .build();

        expenseRepository.save(expense);
        return expense;
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.getReferenceById(id);
    }

    public List<Expense> getExpensesByBudgetId(Long budgetId) {
        return expenseRepository.findByBudgetId(budgetId);
    }

    @Transactional
    public void updateExpense(Long id, ExpenseRequest request) {
        Expense expense = getExpenseById(id);
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
