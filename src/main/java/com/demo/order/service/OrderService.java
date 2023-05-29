package com.demo.order.service;

import com.demo.order.constant.OrderStatus;
import com.demo.order.event.OrderStatusEvent;
import com.demo.order.exception.Error;
import com.demo.order.exception.ServiceException;
import com.demo.order.model.OrderCreateRequestDTO;
import com.demo.order.model.OrderMapper;
import com.demo.order.model.ProductQuantityRequestDTO;
import com.demo.order.repository.OrderProductRepository;
import com.demo.order.repository.OrderRepository;
import com.demo.order.repository.ProductRepository;
import com.demo.order.repository.entity.Order;
import com.demo.order.repository.entity.OrderProduct;
import com.demo.order.repository.entity.OrderProductKey;
import com.demo.order.repository.entity.Product;
import com.demo.order.util.CodeGenerator;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@AllArgsConstructor
public class OrderService implements IOrderService {

    public final OrderRepository orderRepository;
    public final ProductRepository productRepository;
    public final OrderProductRepository orderProductRepository;
    public final OrderMapper orderMapper;
    public final ApplicationEventPublisher applicationEventPublisher;


    //Assume rule for to set status, example if OrderStatus is CONFIRMED, previous status must have PLACED
    public final Map<OrderStatus, OrderStatus> orderStatusRules =
            Map.ofEntries(Map.entry(OrderStatus.CONFIRMED, OrderStatus.PLACED),
                    Map.entry(OrderStatus.DELIVERED, OrderStatus.CONFIRMED),
                    Map.entry(OrderStatus.SHIPPED, OrderStatus.DELIVERED));


    /**
     * Request created an order
     *
     * @param requestDTO
     */
    @Override
    @Transactional
    public String createOrder(OrderCreateRequestDTO requestDTO) {
        Order order = new Order();
        order.setCode(CodeGenerator.generateCode());
        order.setStatus(OrderStatus.PLACED.getStatus());
        order.setCustomerId(requestDTO.getUserId());
        order.setShippingAddress(requestDTO.getShippingAddress());
        Order orderCreated = orderRepository.save(order);

        if (requestDTO.getProducts().isEmpty()) throw new ServiceException(Error.PRODUCT_EMPTY);

        for (ProductQuantityRequestDTO p : requestDTO.getProducts()) {
            Product product = productRepository
                    .findById(p.getProductId())
                    .orElseThrow(() -> new ServiceException(Error.PRODUCT_INVALID));
            OrderProductKey orderProductKey = new OrderProductKey(orderCreated.getOrderId(), product.getProductId());
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setProduct(product);
            orderProduct.setOrder(order);
            orderProduct.setId(orderProductKey);
            orderProduct.setQuantity(p.getQuantity());
            orderProductRepository.save(orderProduct);
        }
        return orderCreated.getCode();

    }

    /**
     * Find Order by unique code
     *
     * @param code
     * @return
     */
    @Override
    public Order findByCode(String code) {
        return orderRepository.findOrderByCode(code).orElseThrow(() -> new ServiceException(Error.ORDER_NOT_EXISTS));
    }

    /**
     * Update order status
     *
     * @param status
     * @param orderId
     * @param userId
     * @return older status state
     */
    @Override
    @Transactional
    public void updateState(OrderStatus status, Integer orderId, Integer userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ServiceException(Error.ORDER_NOT_EXISTS));
        if (!userId.equals(order.getCustomerId())) throw new ServiceException(Error.USER_NOT_PERMISSION);
        if (orderStatusRules.get(status) == null || !orderStatusRules.get(status).getStatus().equals(order.getStatus())) {
            throw new ServiceException(Error.ORDER_STATUS_INVALID);
        }
        order.setStatus(status.getStatus());
        var orderUpdate = orderRepository.save(order);

        //Public event for notification
        ApplicationEvent event = new OrderStatusEvent(orderMapper.fromOrder(orderUpdate));
        applicationEventPublisher.publishEvent(event);
    }
}
