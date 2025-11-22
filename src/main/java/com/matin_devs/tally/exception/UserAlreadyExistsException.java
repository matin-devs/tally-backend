package com.matin_devs.tally.exception;

public class UserAlreadyExistsException extends Exception{
    public UserAlreadyExistsException() {
        super("Username already exists, user creation unsuccessful");
    }
}
