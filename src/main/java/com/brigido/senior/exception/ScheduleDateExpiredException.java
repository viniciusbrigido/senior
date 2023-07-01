package com.brigido.senior.exception;

public class ScheduleDateExpiredException extends RuntimeException {

    public ScheduleDateExpiredException(String message) {
        super(message);
    }
}
