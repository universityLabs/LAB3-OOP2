package com.tech.zootech.customerservice.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.tech.zootech.customerservice.domain.data.CustomerRegistrationData;
import com.tech.zootech.customerservice.domain.entity.Customer;
import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.exceptions.CannotParseFileException;
import com.tech.zootech.customerservice.exceptions.InvalidCustomerRegistrationDataException;
import com.tech.zootech.customerservice.exceptions.InvalidFileException;
import com.tech.zootech.customerservice.manager.SenderManager;
import com.tech.zootech.customerservice.repository.CustomerRepository;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.CustomerOnboarding;
import com.tech.zootech.customerservice.service.CustomerValidator;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Objects;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CustomerOnboardingImpl implements CustomerOnboarding {
    public static final String CSV_EXTENSION = ".csv";
    RegistrationHistoryRepository registrationHistoryRepository;
    CustomerRepository customerRepository;
    CustomerValidator customerValidator;
    SenderManager senderManager;

    @Override
    public void onboard(CustomerRegistrationData customerData, InputStream inputStream) {
        if (!customerValidator.validateRegistrationData(customerData)) {
            handleInvalidCustomer(customerData);
        } else {
            saveData(customerData);
            sendSuccessfulEmail(customerData, inputStream);
        }
    }

    @Override
    @Async("onboardCustomersExecutor")
    public void processFile(String fileName, InputStream inputStream) {
        if (Objects.isNull(fileName)) {
            throw new InvalidFileException("No file name");
        }
        try (final var inputStreamReader = new InputStreamReader(inputStream)) {
            final var csvCustomers = new CsvToBeanBuilder<CustomerRegistrationData>(inputStreamReader)
                    .withType(CustomerRegistrationData.class)
                    .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
                    .withSkipLines(1)
                    .build();
            csvCustomers.forEach(customer -> onboard(customer, inputStream));
        } catch (IOException e) {
            throw new CannotParseFileException("Cannot parse file");
        }
    }

    private void sendSuccessfulEmail(CustomerRegistrationData customerData, InputStream inputStream) {
        if (Objects.nonNull(inputStream)) {
            senderManager.sendCompleteOnboardingEmail(StringUtils.EMPTY, customerData.getEmail(), getFileName(), inputStream);
        } else {
            senderManager.sendCompleteOnboardingEmail(StringUtils.EMPTY, customerData.getEmail());
        }
    }

    private void saveData(CustomerRegistrationData customerData) {
        log.info("Customer with id: {} has been validated!", customerData.getId());
        final var customer = new Customer(customerData);
        customerRepository.saveAndFlush(customer);
        registrationHistoryRepository.saveAndFlush(new RegistrationHistory(RegistrationStatus.SUCCESS, customer));
        log.info("Customer with id: {} has been saved to db!", customerData.getId());
    }

    private void handleInvalidCustomer(CustomerRegistrationData customerData) {
        registrationHistoryRepository.saveAndFlush(new RegistrationHistory(RegistrationStatus.ERROR, null));
        senderManager.sendErrorOnboardingEmail(StringUtils.EMPTY, StringUtils.EMPTY, customerData.getEmail());
        throw new InvalidCustomerRegistrationDataException("Customer registration data is not valid! Please, check data again");
    }

    private String getFileName() {
        return "Onboarding_Result_" + LocalDateTime.now() + CSV_EXTENSION;
    }
}
