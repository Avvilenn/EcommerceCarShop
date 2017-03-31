package com.carShop.exception;


public class CarOrderDaoException extends Exception {
    private long code;

    public CarOrderDaoException(String message, long code) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
