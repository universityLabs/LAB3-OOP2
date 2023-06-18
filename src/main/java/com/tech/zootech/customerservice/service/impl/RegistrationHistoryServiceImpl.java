package com.tech.zootech.customerservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationStatisticsDTO;
import com.tech.zootech.customerservice.domain.entity.RegistrationHistory;
import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.exceptions.RegistrationHistoryNotFoundException;
import com.tech.zootech.customerservice.repository.RegistrationHistoryRepository;
import com.tech.zootech.customerservice.service.RegistrationHistoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegistrationHistoryServiceImpl implements RegistrationHistoryService {
    public static final String CONTENT_TYPE = "application/json";
    private final RegistrationHistoryRepository registrationHistoryRepository;
    private final ObjectMapper objectMapper;

    @Override
    public RegistrationHistoryDTO getByCustomerId(Long id) {
        return new RegistrationHistoryDTO(registrationHistoryRepository.findById(id)
                .orElseThrow(() -> new RegistrationHistoryNotFoundException("Registration history not found!")));
    }

    @Override
    public List<RegistrationStatisticsDTO> getStatistics() {
        return registrationHistoryRepository.groupByStatus()
                .stream()
                .map(tuple -> new RegistrationStatisticsDTO (
                        tuple.get(0, RegistrationStatus.class),
                        tuple.get(1, Long.class).intValue()
                ))
                .toList();
    }

    @Override
    public List<RegistrationHistoryDTO> getById(Long id) {
        final Optional<RegistrationHistory> history = registrationHistoryRepository.findById(id);
        return history.map(registrationHistory -> Collections.singletonList(new RegistrationHistoryDTO(registrationHistory)))
                .orElse(Collections.emptyList());
    }
}
