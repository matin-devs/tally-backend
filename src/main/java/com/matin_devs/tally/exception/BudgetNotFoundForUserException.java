package com.matin_devs.tally.exception;

import com.matin_devs.tally.model.User;

public class BudgetNotFoundForUserException extends RuntimeException {
    public BudgetNotFoundForUserException(User user) {
        super("Unable to retrieve budget for user: [name: " + user.getUsername() + ", id: " + user.getId() + "]");
    }
}
