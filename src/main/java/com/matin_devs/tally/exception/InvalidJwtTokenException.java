package com.matin_devs.tally.exception;

public class InvalidJwtTokenException extends RuntimeException {
    public InvalidJwtTokenException(String token) {
        super("Invalid Token with value: " + token);
    }
}
