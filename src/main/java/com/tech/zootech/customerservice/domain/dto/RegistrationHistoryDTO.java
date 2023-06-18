package com.tech.zootech.customerservice.domain.dto;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.domain.interfaces.IRegistrationHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationHistoryDTO implements IRegistrationHistory {
    private RegistrationStatus status;
    private CustomerDto customer;

    public RegistrationHistoryDTO(IRegistrationHistory history) {
        this.status = history.getStatus();
        this.customer = new CustomerDto(history.getCustomer(), Collections.emptyList());
    }
}
