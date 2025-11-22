package com.matin_devs.tally.dto;

import com.matin_devs.tally.common.ExpenseCategory;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class ExpenseRequest {
    private String title;
    private ExpenseCategory category;
    private Float amount;
    private LocalDate date;
}
