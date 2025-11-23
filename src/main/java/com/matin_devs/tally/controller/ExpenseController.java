package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.exception.ExpenseNotFoundException;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.service.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getExpense(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(expenseService.getExpenseById(id).toString());
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Expense not found");
        }
    }

    @GetMapping("/budget/{budgetId}")
    public ResponseEntity<List<Expense>> getExpensesByBudgetId(@RequestParam Long budgetId) {
        List<Expense> budgetExpenses = expenseService.getExpensesByBudgetId(budgetId);
        return ResponseEntity.ok(budgetExpenses);
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest request) {
        // TODO: Should check if expense is already added
        // COMMENT: Should not be required, as duplicate transactions are a possibility
        Expense expense = expenseService.addExpense(request);
        return ResponseEntity.ok(expense.toString());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateExpense(@PathVariable Long id, ExpenseRequest request) {
        try {
            expenseService.updateExpense(id, request);
            return ResponseEntity.ok("Expense with id " + id + " successfully updated");
        } catch (ExpenseNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expense not found");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        try {
            expenseService.deleteExpense(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(id.toString());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Exception ID is not valid");
        }
    }
}
