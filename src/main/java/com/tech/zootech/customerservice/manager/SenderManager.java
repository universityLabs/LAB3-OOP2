package com.tech.zootech.customerservice.manager;

import java.io.InputStream;

public interface SenderManager {
    void sendCompleteOnboardingEmail(String from, String to, String fileName, InputStream inputStream);
    void sendCompleteOnboardingEmail(String from, String to);
    void sendErrorOnboardingEmail(String from, String to, String customerEmail);
}
