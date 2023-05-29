package com.demo.order.controller;

import com.demo.order.constant.OrderStatus;
import com.demo.order.model.OrderCreateRequest;
import com.demo.order.model.OrderCreateRequestDTO;
import com.demo.order.model.OrderDetailResponse;
import com.demo.order.model.OrderMapper;
import com.demo.order.model.base.BaseResponse;
import com.demo.order.service.IOrderService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import static com.demo.order.constant.UrlPath.ORDER;

@RestController
@RequestMapping(ORDER)
@Log4j2
public class OrderController {

    public final IOrderService orderService;
    public final OrderMapper orderMapper;

    public OrderController(IOrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping
    public BaseResponse<String> createdOrder(@RequestBody OrderCreateRequest request) {
        log.info("Start to create order {}", request.toString());
        OrderCreateRequestDTO requestDTO = orderMapper.from(request);
        var orderCode = orderService.createOrder(requestDTO);
        return BaseResponse.ofSucceeded(String.format("Create order successfully. Order code is :  %s", orderCode));
    }

    @GetMapping("/{code}")
    public BaseResponse<OrderDetailResponse> findByCode(@PathVariable String code) {
        log.info("Call filter order by code {}", code);
        var orderDetail = orderService.findByCode(code);
        return BaseResponse.ofSucceeded(orderMapper.fromOrder(orderDetail));
    }

    @PutMapping("/{orderId}")
    public BaseResponse<String> updateOrderStatus(@PathVariable Integer orderId, @RequestParam("status") String status, @RequestParam("user_id") Integer userId) {
        log.info("User {} update order {} status {}", userId, orderId, status);
        orderService.updateState(OrderStatus.valueOf(status), orderId, userId);
        return BaseResponse.ofSucceeded(String.format("Update order status %s successfully", status));
    }


}
