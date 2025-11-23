package com.matin_devs.tally.dto;

import com.matin_devs.tally.common.ExpenseCategory;
import com.matin_devs.tally.model.Budget;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
public class ExpenseRequest {
    private String title;
    private ExpenseCategory category;
    private Float amount;
    private LocalDate date;
    private Set<Long> budgetId;
}
