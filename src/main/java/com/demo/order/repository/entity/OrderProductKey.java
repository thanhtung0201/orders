package com.demo.order.repository.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderProductKey implements Serializable {

    @Column(name = "order_id")
    Integer orderId;

    @Column(name = "product_id")
    Integer productId;

}
