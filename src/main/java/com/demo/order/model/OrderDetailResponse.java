package com.demo.order.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrderDetailResponse {

    private Integer orderId;
    private String code;
    private String shippingAddress;
    private Integer customerId;
    private Double totalAmount;
    private String orderStatus;

    private Set<OrderProductResponse> orderProducts;

}
