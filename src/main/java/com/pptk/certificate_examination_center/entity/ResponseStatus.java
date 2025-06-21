package com.pptk.certificate_examination_center.entity;

public enum ResponseStatus {
    SUCCESS("Success"),
    FAIL("Fail"),
    ERROR("Error"),
    NOT_FOUND("Not Found"),
    UNAUTHORIZED("Unauthorized"),
    FORBIDDEN("Forbidden"),
    BAD_REQUEST("Bad Request");

    private final String message;

    ResponseStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
