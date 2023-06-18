package com.tech.zootech.customerservice.domain.interfaces;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;

public interface IRegistrationHistory {
    RegistrationStatus getStatus();
    ICustomer getCustomer();
}
