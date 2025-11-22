package com.matin_devs.tally.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class BudgetRequest {
    private Long userId;
    private Integer frequency;
    private Set<Long> expenseList;
}
