package com.demo.order.model.base;

import com.demo.order.exception.Error;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class BaseResponse<T> {

    private T data;
    private Metadata meta = new Metadata();
    public static final int OK_CODE = 200;

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<T> ofError(Error error) {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = error.getErrorCode();
        response.meta.internalMessage = error.getDesc();
        return response;
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata {
        private int code;
        private String internalMessage;
    }

}
