package com.demo.order.constant;

import java.util.Objects;

public enum OrderStatus {

    PLACED((byte) 1), CONFIRMED((byte) 2), SHIPPED((byte) 3), DELIVERED((byte) 4);

    private final Byte status;

    OrderStatus(Byte status) {
        this.status = status;
    }

    public Byte getStatus() {
        return status;
    }

    public String getValue(Byte status) {
        for(OrderStatus e: OrderStatus.values()) {
            if(Objects.equals(e.status, status)) {
                return e.toString();
            }
        }
        return null;
    }
}
