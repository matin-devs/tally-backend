package com.matin_devs.tally.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class BudgetRequest {
    private UUID userId;
    private Integer frequency;
    private Set<UUID> expenseIdList;
}
