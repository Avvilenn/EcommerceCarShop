package com.carShop.exception;


public class CarBusinessException extends Exception {
    private long code;

    public CarBusinessException(String message, long code) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
