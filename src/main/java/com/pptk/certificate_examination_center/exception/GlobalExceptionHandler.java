package com.pptk.certificate_examination_center.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(value = {CustomerNotFoundException.class, ScheduleNotFoundException.class,
    CandidateNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(
                NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(errorResponse, NOT_FOUND);
    }
}
