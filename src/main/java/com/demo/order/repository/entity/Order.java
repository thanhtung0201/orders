package com.demo.order.repository.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "customer_id")
    private Integer customerId;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<OrderProduct> orderProducts;

    @Column(name = "status")
    private Byte status;

}
