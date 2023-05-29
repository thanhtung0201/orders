package com.demo.order.service;

public interface IEmailService {
    void sendMail(String email, String subject, String content);
}
