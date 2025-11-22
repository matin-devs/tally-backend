package com.matin_devs.tally.exception;

public class ExpenseNotFoundException extends RuntimeException {
    public ExpenseNotFoundException(Long id) {
        super("Expense with " + id + "not found");
    }
}
