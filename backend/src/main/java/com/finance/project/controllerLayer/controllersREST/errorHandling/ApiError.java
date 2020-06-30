package com.finance.project.controllerLayer.controllersREST.errorHandling;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ApiError {

    // status: the HTTP status code
    // message: the error message associated with exception
    //error: List of constructed error messages

    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(HttpStatus status, String message, List<String> errors) {
        super();
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(HttpStatus status, String message, String error) {
        super();
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiError apiError = (ApiError) o;
        return getStatus() == apiError.getStatus() &&
                Objects.equals(getMessage(), apiError.getMessage()) &&
                Objects.equals(getErrors(), apiError.getErrors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage(), getErrors());
    }
}
