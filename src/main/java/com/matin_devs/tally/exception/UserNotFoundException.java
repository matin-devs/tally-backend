package com.matin_devs.tally.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("Username " + username + " not found, please try again with a different username");
    }

    public UserNotFoundException(Long id) {
        super("Id " + id + " not found, please try again with a different id");
    }
}
