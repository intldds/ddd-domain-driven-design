package com.finance.project.domainLayer.exceptions;

public class TransactionsNotFoundException extends RuntimeException{

    public TransactionsNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
