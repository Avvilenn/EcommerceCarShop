package com.carShop.exception;


public class CarDaoException extends Exception {
    private long code;

    public CarDaoException(String message, long code) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
