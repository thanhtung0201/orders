package com.demo.order.service;

import com.demo.order.constant.OrderStatus;
import com.demo.order.model.OrderCreateRequestDTO;
import com.demo.order.repository.entity.Order;

public interface IOrderService {

    String createOrder(OrderCreateRequestDTO requestDTO);

    Order findByCode(String code);

    void updateState(OrderStatus status, Integer orderId, Integer userId);

}
