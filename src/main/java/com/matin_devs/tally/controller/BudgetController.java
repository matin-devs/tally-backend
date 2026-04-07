package com.matin_devs.tally.controller;

import com.matin_devs.tally.exception.BudgetNotFoundForUserException;
import com.matin_devs.tally.model.Budget;
import com.matin_devs.tally.model.TransactionCategory;
import com.matin_devs.tally.model.User;
import com.matin_devs.tally.service.BudgetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/budget")
@AllArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    // get Budget from User ID
    //TODO: Should be updated to return Budget Response by abstracting away sensitive information
    @GetMapping
    public ResponseEntity<Budget> getBudgetByUserId(@AuthenticationPrincipal User user) {
        try {
            Budget budget = budgetService.getBudgetByUserId(user.getId());
            return ResponseEntity.ok(budget);
        } catch (BudgetNotFoundForUserException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping()
    public ResponseEntity<String> updateBudgetByCategory(@AuthenticationPrincipal User user, TransactionCategory category, BigDecimal newAmount) {
        budgetService.updateBudgetCategory(user, category, newAmount);
        return ResponseEntity.ok("Budget Category " + category + " Updated with amount " + newAmount);
    }
}
