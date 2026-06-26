package com.jobPortal.custom_exceptions;

public class InvalidInputException extends RuntimeException {

    public InvalidInputException(String errMsg) {
        super(errMsg);
    }
}
