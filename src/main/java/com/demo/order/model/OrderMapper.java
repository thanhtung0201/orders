package com.demo.order.model;

import com.demo.order.constant.OrderStatus;
import com.demo.order.repository.entity.Order;
import com.demo.order.repository.entity.OrderProduct;
import com.demo.order.repository.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderCreateRequestDTO from(OrderCreateRequest request);

    OrderProductResponse fromProduct(Product product);

    @Mapping(source = "orderProducts", target = "orderProducts", qualifiedByName = "mappingProducts")
    @Mapping(source = "orderProducts", target = "totalAmount", qualifiedByName = "mappingAmount")
    @Mapping(source = "status", target = "orderStatus", qualifiedByName = "mappingOrderStatus")
    OrderDetailResponse fromOrder(Order order);

    @Named("mappingOrderStatus")
    default String formStatus(Byte status) {
        return OrderStatus.PLACED.getValue(status);
    }

    @Named("mappingProducts")
    default Set<OrderProductResponse> mapProduct(Set<OrderProduct> orderProducts) {
        return orderProducts.stream().map(op ->
        {
            var product = this.fromProduct(op.getProduct());
            product.setQuantity(op.getQuantity());
            return product;
        }).collect(Collectors.toSet());
    }

    @Named("mappingAmount")
    default Double amount(Set<OrderProduct> orderProducts) {
        return orderProducts.stream().map(od -> od.getProduct().getPrice() * od.getQuantity())
                .reduce(0.0d,
                        Double::sum);
    }

}
