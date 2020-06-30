package com.finance.project.domainLayer.exceptions;

public class InvalidArgumentsBusinessException extends RuntimeException {

    public InvalidArgumentsBusinessException(String message) {
        super(message);
    }
}
