package com.tech.zootech.customerservice.manager.impl;

import com.tech.zootech.customerservice.exceptions.EmailSendException;
import com.tech.zootech.customerservice.manager.SenderManager;
import com.tech.zootech.customerservice.service.EmailSenderService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SenderManagerImpl implements SenderManager {
    @Value("${spring.mail.username}")
    private String adminEmail;
    EmailSenderService emailSenderService;

    private String getFrom(String from) {
        if (Objects.nonNull(adminEmail) && !adminEmail.isEmpty()) {
            return adminEmail;
        } else {
            return from;
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void sendCompleteOnboardingEmail(String from, String to, String fileName, InputStream inputStream) {
        try {
            emailSenderService.sendWithAttach(getFrom(from), to, "Welcome email!", "Welcome! Happy discovering!", fileName, new InputStreamResource(inputStream));
        } catch (MessagingException | IOException e) {
            throw new EmailSendException(e.getMessage());
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void sendCompleteOnboardingEmail(String from, String to) {
        emailSenderService.send(getFrom(from), to, "Welcome email!", "Welcome! Happy discovering!");
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void sendErrorOnboardingEmail(String from, String to, String customerEmail) {
        emailSenderService.send(getFrom(from), getFrom(to), "Error email!", "Error! while onboarding customer with email: " + customerEmail);
    }
}
