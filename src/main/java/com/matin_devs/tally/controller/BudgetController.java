package com.matin_devs.tally.controller;

import com.matin_devs.tally.dto.BudgetRequest;
import com.matin_devs.tally.exception.BudgetNotFoundForUserException;
import com.matin_devs.tally.model.Budget;
import com.matin_devs.tally.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @PostMapping
    public ResponseEntity<String> addBudget(@RequestBody BudgetRequest request) {
        try {
            budgetService.getBudgetByUserId(request.getUserId());
            return ResponseEntity.noContent().build();
        } catch (BudgetNotFoundForUserException e) {
            budgetService.addBudget(request);
            return ResponseEntity.ok("Budget Created for user id: [" + request.getUserId() + "]");
        }
    }

    // get Budget from User ID
    @GetMapping("/{userId}")
    public ResponseEntity<Budget> getBudgetByUserId(@PathVariable UUID userId) {
        try {
            Budget budget = budgetService.getBudgetByUserId(userId);
            return ResponseEntity.ok(budget);
        } catch (BudgetNotFoundForUserException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateBudgetById(@PathVariable UUID id,@RequestBody BudgetRequest request) {
        budgetService.updateBudgetById(id, request);
        return ResponseEntity.ok("Budget [" + id + "] Updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudgetById(@PathVariable UUID id) {
        budgetService.deleteBudgetById(id);
        return ResponseEntity.ok("Budget [" + id + "] Deleted");
    }
}
