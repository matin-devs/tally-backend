package com.matin_devs.tally.dto;

import com.matin_devs.tally.common.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

// TODO: Link Budget ID
@Data
@AllArgsConstructor
public class ExpenseRequest {
    private String title;
    private ExpenseCategory category;
    private Float amount;
    private LocalDate date;
}
