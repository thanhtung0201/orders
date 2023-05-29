package com.demo.order.exception;

import com.demo.order.model.base.BaseResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order(10)
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ServiceException.class, RuntimeException.class})
    public ResponseEntity<Object> handleServiceException(
            Exception ex, WebRequest request) {
        if (ex instanceof ServiceException) {
            Error error = ((ServiceException) ex).getError();
            return new ResponseEntity<>(
                    BaseResponse.ofError(error), error.getHttpCode());
        } else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
