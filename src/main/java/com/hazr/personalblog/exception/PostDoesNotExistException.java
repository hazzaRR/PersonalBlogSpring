package com.hazr.personalblog.exception;

public class PostDoesNotExistException extends RuntimeException {
    public PostDoesNotExistException(String message) {
        super(message);
    }
}
