package com.tech.zootech.customerservice.domain.dto;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegistrationStatisticsDTO {
    private RegistrationStatus status;
    private Integer count;
}
