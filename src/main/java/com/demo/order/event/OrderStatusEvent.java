package com.demo.order.event;

import com.demo.order.model.OrderDetailResponse;
import org.springframework.context.ApplicationEvent;

public class OrderStatusEvent extends ApplicationEvent {

    private OrderDetailResponse order;

    public OrderStatusEvent(OrderDetailResponse order) {
        super(order);
        this.order = order;
    }

    public OrderDetailResponse getOrder() {
        return order;
    }
}
