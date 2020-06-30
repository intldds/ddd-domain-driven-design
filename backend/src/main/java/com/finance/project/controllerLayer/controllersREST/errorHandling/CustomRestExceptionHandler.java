package com.finance.project.controllerLayer.controllersREST.errorHandling;


import com.finance.project.domainLayer.exceptions.InvalidArgumentsBusinessException;
import com.finance.project.domainLayer.exceptions.NotFoundArgumentsBusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Exception handler in order to customize some business and non business exceptions
 */

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {


    //ResponseEntityExceptionHandler - it provides methods for handling the exceptions, but it allows the developer to specify
    // ResponseEntities as return values. The implementation is still straight forward, create a class, annotate it with the
    // @ControllerAdvice, extend the ResponseEntityExceptionHandler class and override the methods with the exception types
    // that you are interested in.

    /**
     * This method is to handle all the scenarios when a client sends an invalid request to the API, providing our own custom message.
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception,
                                                                  final HttpHeaders headers,
                                                                  final HttpStatus status, final WebRequest request) {


        //MethodArgumentNotValidException - MethodArgumentNotValidException exception as the exception to be handled. In consequence,
        // Spring Boot will call this method when the specified User object is invalid.
        //The method stores the name and post-validation error message of each invalid field in a Map. Next, it sends the Map back to
        // the client as a JSON representation for further processing.
        // Exception to be thrown when validation on an argument annotated with @Valid fails.

        //HttpHeaders - A data structure representing HTTP request or response headers, mapping String header names to a list of
        // String values, also offering accessors for common application-level data types.
        //HttpStatus - Enumeration of HTTP status codes.
        //WebRequest - A request sent to a web server. Constructs a web request using an absolute URL string.

        final List<String> errors = new ArrayList<>();

        //FieldError - Encapsulates a field error, that is, a reason for rejecting a specific field value.
        for (final FieldError error : exception.getBindingResult().getFieldErrors()) { //getBinding - Return the results of the failed validation.
            errors.add(error.getField() + " : " + error.getDefaultMessage());
            //getField - Return the affected field of the object.
            //getMessage -  It can also contain a message string that gives more information about the error.
        }

        //ObjectError - Encapsulates an object error, that is, a global reason for rejecting an object.
        for (final ObjectError error : exception.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + " : " + error.getDefaultMessage());
            //getObjectName - Return the name of the affected object.
            //defaultMessage - the default message to be used to resolve this message
        }

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getLocalizedMessage(), errors);
        //getLocalizedMessage - returns the name of the exception in the local language of the user (Portuguese, English, Spanish, etc.).

        return handleExceptionInternal(exception, apiError, headers, apiError.getStatus(), request);
    }

    //@ExceptionHandler - When declared within a controller such methods apply to exceptions raised by @RequestMapping methods of that controller
    // (or any of its sub-classes). You can also declare an @ExceptionHandler method within an @ControllerAdvice class
    // in which case it handles exceptions from @RequestMapping methods from any controller.

    /**
     * Customize message for a business exception with an Http status -> 422 / UNPROCESSABLE_ENTITY
     * @param exception
     * @return
     */

    @ExceptionHandler(InvalidArgumentsBusinessException.class)
    public final ResponseEntity<Object> handleInvalidArgumentsBusinessException(InvalidArgumentsBusinessException exception) {
        final HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;

//  1. Create payload containing exception details
//          Create a custom message for NOT FOUND ERROR

        final String error = "Status Code: " + status.value() + ", Exception: InvalidArgumentsBusinessException";

//        Wrap all the information to send to the client

        final ApiError apiError = new ApiError(status, exception.getLocalizedMessage(), error);

        // 2. Return response entity

        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }

    /**
     * Customize message for a business exception with an Http status -> 404 | NOT_FOUND
     * @param exception
     * @return
     */

    @ExceptionHandler(NotFoundArgumentsBusinessException.class)
    public final ResponseEntity<Object> handleNotFoundArgumentsBusinessException(NotFoundArgumentsBusinessException exception) {
        final HttpStatus status = HttpStatus.NOT_FOUND;

//  1. Create payload containing exception details

//        Create a custom message for NOT FOUND ERROR

        final String error = "Status Code: " + status.value() + ", Exception: NotFoundArgumentsBusinessException";

//        Wrap all the information to send to the client

        final ApiError apiError = new ApiError(status, exception.getLocalizedMessage(), error);

        return new ResponseEntity<>(apiError, new HttpHeaders(), status);
    }

    /**
     * Customize message for a business exception with an Http status -> 500 | INTERNAL_SERVER_ERROR
     * @param exception
     * @param request
     * @return
     */


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {

//  1. Create payload containing exception details

//         Create a custom message for INTERNAL SERVER ERROR

        String message = "This is an INTERNAL SERVER ERROR, some error occurred. " +
                "Please don't forget to also verify the information sent (see message).";


//        Wrap all the information to send to the client

        ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, exception.getLocalizedMessage(), message);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Customize message for an exception with an Http status -> 400 | MALFORMED JSON
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

//  1. Create payload containing exception details
//          Create a custom message for Malformed JSON exception

        String message = "Please verify the information sent.";

//          Create a customized error message for this exception, informing the line and the character that probably has
//        the error
        String error = "Malformed JSON request - verify on " +
                exception.getLocalizedMessage().substring(exception.getLocalizedMessage().length() - 20,
                        exception.getLocalizedMessage().length() - 1);

//        String error = "Malformed JSON request - verify on " + ex.getLocalizedMessage().substring(294, 313);

//        Wrap all the information to send to the client

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message, error);

        return new ResponseEntity<Object>(apiError, apiError.getStatus());
    }

    /**
     * Customize message for an exception with an Http status -> 405 | METHOD_NOT_ALLOWED
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException exception,
                                                                         HttpHeaders headers, HttpStatus status,
                                                                         WebRequest request) {
//  1. Create payload containing exception details

//        Create a custom message for METHOD_NOT_ALLOWED EXCEPTION

        StringBuilder builder = new StringBuilder();

//        Add to the message the method selected by the client, that triggered this exception handler

        builder.append(exception.getMethod());

        builder.append(" method is not supported for this request. Supported methods are ");

//        Get all the supported methods, and add them to the error message to send to the client

        exception.getSupportedHttpMethods().forEach(supportedMethod -> builder.append(supportedMethod + " "));

//        Wrap all the information to send to the client

        ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, exception.getLocalizedMessage(), builder.toString());

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    /**
     * Customize message for an exception with an Http status -> 415 | UNSUPPORTED_MEDIA_TYPE
     * @param exception
     * @param headers
     * @param status
     * @param request
     * @return
     */

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException exception,
                                                                     HttpHeaders headers, HttpStatus status,
                                                                     WebRequest request) {
        //  1. Create payload containing exception details

//        Create a custom message for UNSUPPORTED_MEDIA_TYPE EXCEPTION

        StringBuilder builder = new StringBuilder();

//        Add to the message the method selected by the client, that triggered this exception handler

        builder.append(exception.getContentType());
        builder.append(" media type is not supported. Supported media types are ");

//        Get all the media type supported, and add them to the error message to send to the client

        exception.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

//                Wrap all the information to send to the client

        ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
                exception.getLocalizedMessage(), builder.substring(0, builder.length() - 2));

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }



}