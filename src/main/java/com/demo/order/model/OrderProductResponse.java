package com.demo.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProductResponse {

    private String code;

    private String name;

    private Double price;

    private int quantity;

}
