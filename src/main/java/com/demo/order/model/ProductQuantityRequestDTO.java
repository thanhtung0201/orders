package com.demo.order.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductQuantityRequestDTO {

    private Integer productId;

    private Integer quantity;

}
