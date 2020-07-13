package com.finance.project.domainLayer.exceptions;


public class NotFoundArgumentsBusinessException extends RuntimeException{

    public NotFoundArgumentsBusinessException (String message) {
        super(message);
    }
}
