package com.carShop.exception;


public class PersonDaoException extends Exception{
    private long code;
    public PersonDaoException(long code, String message) {
        super(message);
        this.code = code;
    }
    public long getCode() {
        return code;
    }
}
