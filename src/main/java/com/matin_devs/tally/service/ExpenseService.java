package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.repository.ExpenseRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public Expense createExpense(ExpenseRequest request) {

        Expense expense = Expense.builder()
                .timestamp(request.getTimestamp())
                .title(request.getTitle())
                .category(request.getCategory())
                .amount(request.getAmount())
                .build();

        expenseRepository.save(expense);
        return expense;
    }

    public Expense getExpenseById(UUID id) {
        return expenseRepository.getReferenceById(id);
    }

    public List<Expense> getExpensesByBudgetId(UUID budgetId) {
        return expenseRepository.findByBudgetId(budgetId);
    }

    @Transactional
    public void updateExpenseById(UUID id, ExpenseRequest request) {
        Expense expense = getExpenseById(id);
        expense.setTimestamp(request.getTimestamp());
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
    }

    public void deleteExpenseById(UUID id) {
        expenseRepository.deleteById(id);
    }
}
