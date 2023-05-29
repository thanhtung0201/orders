package com.demo.order.repository;

import com.demo.order.repository.entity.OrderProduct;
import com.demo.order.repository.entity.OrderProductKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductKey> {
}
