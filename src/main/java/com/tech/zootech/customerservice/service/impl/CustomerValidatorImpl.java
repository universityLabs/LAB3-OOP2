package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.service.CustomerValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.micrometer.common.util.StringUtils.isEmpty;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class CustomerValidatorImpl implements CustomerValidator {

    @Override
    public boolean validateRegistrationData(CustomerRegistrationData customerData) {
        return !isValidRegistrationData(customerData);
    }

    private static boolean isValidRegistrationData(CustomerRegistrationData customerData) {
        return nonNull(customerData.getId()) && customerData.getId() > 0
                && nonNull(customerData.getEmail()) && !isEmpty(customerData.getEmail())
                && nonNull(customerData.getFirstName()) && nonNull(customerData.getLastName());
    }
}
