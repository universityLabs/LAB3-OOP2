package com.tech.zootech.customerservice.service;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import java.io.InputStream;

public interface CustomerOnboarding {
    void onboard(CustomerRegistrationData customerData, InputStream inputStream);
    void processFile(String fileName, InputStream inputStream);
}
