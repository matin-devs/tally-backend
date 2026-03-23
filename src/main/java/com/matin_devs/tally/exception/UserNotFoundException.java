package com.matin_devs.tally.exception;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String username) {
        super("Username " + username + " not found, please try again with a different username");
    }

    public UserNotFoundException(UUID id) {
        super("Id: [" + id + "] not found, please try again with a different id");
    }
}
