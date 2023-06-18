package com.tech.zootech.customerservice.service;

import jakarta.mail.MessagingException;
import org.springframework.core.io.InputStreamSource;

import java.io.IOException;

public interface EmailSenderService {
    void send(String from, String to, String subject, String text);

    void sendWithAttach(String from, String to, String subject, String text, String attachName, InputStreamSource inputStream) throws MessagingException, IOException;
}
