package com.jobPortal.custom_exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String errMsg) {
        super(errMsg);
    }
}
