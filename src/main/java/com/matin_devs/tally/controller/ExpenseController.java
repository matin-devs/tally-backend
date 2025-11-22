package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.ExpenseRequest;
import com.matin_devs.tally.exception.ExpenseNotFoundException;
import com.matin_devs.tally.model.Expense;
import com.matin_devs.tally.service.ExpenseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// TODO: A get_all_by_budget_id function
@RestController
@RequestMapping("/expense")
@AllArgsConstructor
public class ExpenseController {

    private ExpenseService expenseService;

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest request) {
        // TODO: Best practice to have service and controller use the same function name
        Expense expense = expenseService.createExpense(request);
        return ResponseEntity.ok(expense.toString());
    }

    // TODO: GetMapping for expenses

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
            // TODO: Add delete functionality to router
            return ResponseEntity.ok(expenseService.getExpenseById(id).getId().toString());
        } catch (ExpenseNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Expense not found");
        }
    }
}
