package com.demo.order.service;

import com.demo.order.util.SecurityGenerator;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Log4j2
public class EmailService implements IEmailService {


    public static final String keyDecrypt = "MWHI+ZFj2kyBtyNwcvFUdtvVmFIi0XL58K4gc+zlO7952HNST+OQuW+JVdq5O/Sp/A78aPRm+4k4GBGaIouaZTllssOc57w3Gxp1oYOZdYU=";
    private final String EMAIL_SENDER = "963258776@7kratom.com";

    @Value(value="${email.secret.key}")
    private String key;

    @Override
    public void sendMail(String emailTo, String subject, String data) {
        String apiKey = SecurityGenerator.decrypt(keyDecrypt, key);
        Email from = new Email(EMAIL_SENDER);
        Email to = new Email(emailTo);
        Content content = new Content("text/plain", data);
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            var res = sg.api(request);
            log.info("Send email successfully to {}", emailTo);
        } catch (IOException ex) {
            log.error("Exception when send email {}", ex.getMessage());
        }
    }
}
