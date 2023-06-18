package com.tech.zootech.customerservice.domain.data;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.domain.interfaces.IRegistrationHistory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationHistoryData implements IRegistrationHistory {
    private RegistrationStatus status;
    private CustomerRegistrationData customer;
}
