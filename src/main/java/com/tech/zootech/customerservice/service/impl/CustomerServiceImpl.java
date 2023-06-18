package com.tech.zootech.customerservice.service.impl;

import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.dto.CustomerDto;
import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.repository.CustomerRepository;
import com.tech.zootech.customerservice.service.CustomerOnboarding;
import com.tech.zootech.customerservice.service.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;
    CustomerOnboarding customerOnboarding;

    @Override
    public CustomerDto registerCustomer(CustomerRegistrationData customerData) {
        customerOnboarding.onboard(customerData, null);
        return new CustomerDto(customerData);
    }

    @Override
    public List<CustomerDto> getAll() {
        return customerRepository.getAllByCreateDate()
                .stream()
                .map(CustomerDto::new)
                .toList();
    }

    @Override
    public List<CustomerFullName> getNames() {
        return customerRepository.getNames();
    }

    @SneakyThrows
    @Override
    public void uploadCustomers(MultipartFile file) {
        try (final InputStream inputStream = file.getInputStream()) {
            customerOnboarding.processFile(file.getOriginalFilename(), inputStream);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
