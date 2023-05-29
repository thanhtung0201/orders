package com.demo.order.exception;


public class ServiceException extends RuntimeException {

    private final Error error;


    public ServiceException(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }


}
