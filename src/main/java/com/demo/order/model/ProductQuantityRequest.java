package com.demo.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductQuantityRequest {

    private Integer productId;

    private Integer quantity;

}
