package com.spring.boot.exception;

public class CustomException extends RuntimeException {

    public CustomException(String message) {
        super(message);
    }
}
