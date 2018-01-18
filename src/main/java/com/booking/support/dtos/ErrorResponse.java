package com.booking.support.dtos;

import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private List<OrchestrationError> errors = new ArrayList<OrchestrationError>();

    public List<OrchestrationError> getErrors() {
        return errors;
    }
}
