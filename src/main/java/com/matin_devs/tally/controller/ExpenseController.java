package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.exception.ExpenseNotFoundException;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getExpenseById(@RequestParam UUID id) {
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id).toString());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<List<Expense>> getExpensesByBudgetId(@RequestParam UUID budgetId) {
        List<Expense> budgetExpenses = expenseService.getExpensesByBudgetId(budgetId);
        return ResponseEntity.ok(budgetExpenses);
    }

    @PostMapping
    public ResponseEntity<String> createExpense(@RequestBody ExpenseRequest request) {
        Expense expense = expenseService.createExpense(request);
        return ResponseEntity.ok(expense.toString());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateExpenseById(@PathVariable UUID id, ExpenseRequest request) {
        try {
            expenseService.updateExpenseById(id, request);
            return ResponseEntity.ok("Expense with id " + id + " successfully updated");
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expense not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpenseById(@PathVariable UUID id) {
        try {
            expenseService.deleteExpenseById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Exception ID is not valid");
        }
    }
}
