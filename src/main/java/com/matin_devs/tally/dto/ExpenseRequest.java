package com.matin_devs.tally.dto;

import com.matin_devs.tally.model.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ExpenseRequest {
    private ZonedDateTime timestamp;
    private String title;
    private TransactionCategory category;
    private BigDecimal amount;
    private Set<UUID> budgetId;
}
