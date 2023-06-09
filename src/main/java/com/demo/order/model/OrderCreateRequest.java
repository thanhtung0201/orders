package com.demo.order.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class OrderCreateRequest {

    private Integer userId;

    private String shippingAddress;

    private List<ProductQuantityRequest> products;

}
