package com.booking.support.dtos;

import java.util.List;

public class OrchestrationError {

    private String errorCode;
    private String errorMessage;
    private List<String> validationResult;

    public OrchestrationError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public OrchestrationError(String errorCode, String errorMessage, List<String> validationResult) {
        this(errorCode, errorMessage);
        this.validationResult = validationResult;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public List<String> getValidationResult() {
        return validationResult;
    }

}
