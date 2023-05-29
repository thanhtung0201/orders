package com.demo.order.exception;

import org.springframework.http.HttpStatus;

public enum Error {

    PRODUCT_INVALID(HttpStatus.BAD_REQUEST, 400, "Product is not exists in system"),
    ORDER_NOT_EXISTS(HttpStatus.NOT_FOUND, 402, "Order is not found in system"),
    USER_NOT_PERMISSION(HttpStatus.FORBIDDEN, 403, "Order has not permission to update order"),
    ORDER_STATUS_INVALID(HttpStatus.BAD_REQUEST, 404, "Order status is invalid"),
    PRODUCT_EMPTY(HttpStatus.BAD_REQUEST, 401, "Products in request can not be empty");

    private final HttpStatus httpStatus;
    private final int errorCode;
    private final String desc;

    Error(HttpStatus httpStatus, int errorCode, String desc) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.desc = desc;
    }

    public HttpStatus getHttpCode() {
        return httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDesc() {
        return desc;
    }
}
