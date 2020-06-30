package com.finance.project.domainLayer.exceptions;


/**
 * Exception triggered when a argument received isn't found in the DB
 * @author Ala Matos
 */

public class NotFoundArgumentsBusinessException extends RuntimeException{

    public NotFoundArgumentsBusinessException (String message) {
        super(message);
    }
}
