package com.matin_devs.tally.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(String username) {
        super("Username " + username + " already exists, user creation unsuccessful");
    }
}
