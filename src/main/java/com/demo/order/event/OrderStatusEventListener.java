package com.demo.order.event;

import com.demo.order.model.OrderDetailResponse;
import com.demo.order.repository.UserInfoRepository;
import com.demo.order.repository.entity.UserInfo;
import com.demo.order.service.IEmailService;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderStatusEventListener implements ApplicationListener<OrderStatusEvent> {


    private final IEmailService iEmailService;
    private final UserInfoRepository userInfoRepository;

    @Override
    public void onApplicationEvent(OrderStatusEvent event) {
        OrderDetailResponse order = event.getOrder();
        Optional<UserInfo> userEvent = userInfoRepository.findById(order.getCustomerId());
        if (userEvent.isPresent()) {
            var user = userEvent.get();
            String content = String.format("Congratulation %s : Your order had updated new status. \n" +
                            " Detail order include code : %s, shipping address: %s, total amount : %s",
                    user.getEmail(), order.getCode(), order.getShippingAddress(), order.getTotalAmount());
            String subject = "New Order Status Update";
            String email = user.getEmail();
            iEmailService.sendMail(email, subject, content);
        }
    }
}
