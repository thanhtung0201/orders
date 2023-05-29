package com.demo.order.repository;

import com.demo.order.repository.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    Optional<Order> findOrderByCode(String code);

}
