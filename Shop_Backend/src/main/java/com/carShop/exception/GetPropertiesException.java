package com.carShop.exception;


public class GetPropertiesException extends Exception {

    private long code;

    public GetPropertiesException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetPropertiesException(long code, String message) {
        super(message);
        this.code= code;
    }

    public long getCode() {
        return code;
    }
}
