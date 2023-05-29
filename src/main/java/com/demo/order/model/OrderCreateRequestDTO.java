package com.demo.order.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderCreateRequestDTO {

    private Integer userId;

    private String shippingAddress;

    private List<ProductQuantityRequestDTO> products;

}
