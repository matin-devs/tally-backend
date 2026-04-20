package com.matin_devs.tally.exception;

import java.util.UUID;

public class TransactionNotFoundException extends RuntimeException {
    public TransactionNotFoundException(UUID id) {
        super("Expense with " + id + "not found");
    }
}

