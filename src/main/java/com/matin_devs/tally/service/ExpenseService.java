package com.matin_devs.tally.service;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.dto.UserRequest;
import com.matin_devs.tally.exception.ExpenseNotFoundException;
import com.matin_devs.tally.exception.UserAlreadyExistsException;
import com.matin_devs.tally.exception.UserNotFoundException;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.repository.ExpenseRepository;
import com.matin_devs.tally.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    public Expense createExpense(ExpenseRequest request) {

        Expense expense = Expense.builder()
                .title(request.getTitle())
                .category(request.getCategory())
                .amount(request.getAmount())
                .date(request.getDate())
                .build();

        expenseRepository.save(expense);
        return expense;
    }

    public Expense getExpenseById(Long id) throws ExpenseNotFoundException {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new ExpenseNotFoundException(id));
    }

    @Transactional
    public void updateExpense(Long id, ExpenseRequest request) {
        Expense expense = getExpenseById(id);
        expense.setTitle(request.getTitle());
        expense.setAmount(request.getAmount());
        expense.setCategory(request.getCategory());
        expense.setDate(request.getDate());
    }
}
