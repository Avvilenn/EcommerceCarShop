package com.carShop.exception;


public class CarOrderItemDaoException extends Exception {
    private long code;

    public CarOrderItemDaoException(Long code, String message) {
        super(message);
        this.code = code;
    }

    public long getCode() {
        return code;
    }
}
