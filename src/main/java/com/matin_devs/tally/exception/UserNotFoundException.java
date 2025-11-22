package com.matin_devs.tally.exception;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("Username not found, please try again with a different username");
    }
}
