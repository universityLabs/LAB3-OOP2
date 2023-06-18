package com.tech.zootech.customerservice.service;

import com.tech.zootech.customerservice.domain.dto.RegistrationHistoryDTO;
import com.tech.zootech.customerservice.domain.dto.RegistrationStatisticsDTO;
import java.util.List;

public interface RegistrationHistoryService {
    RegistrationHistoryDTO getByCustomerId(Long id);
    List<RegistrationStatisticsDTO> getStatistics();
    List<RegistrationHistoryDTO> getById(Long id);
}
