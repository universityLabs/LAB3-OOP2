package com.tech.zootech.customerservice.domain.dto;

import com.tech.zootech.customerservice.domain.interfaces.ICustomer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements ICustomer {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private List<RegistrationHistoryDTO> registrationHistory;

    public CustomerDto(ICustomer iCustomer) {
        this.id = iCustomer.getId();
        this.firstName = iCustomer.getFirstName();
        this.lastName = iCustomer.getLastName();
        this.email = iCustomer.getEmail();
    }

    public CustomerDto(ICustomer iCustomer, List<RegistrationHistoryDTO> registrationHistory) {
        this.id = iCustomer.getId();
        this.firstName = iCustomer.getFirstName();
        this.lastName = iCustomer.getLastName();
        this.email = iCustomer.getEmail();
        this.registrationHistory = Objects.isNull(registrationHistory) || registrationHistory.isEmpty() ? null :
                registrationHistory
                        .stream()
                        .map(RegistrationHistoryDTO::new)
                        .toList();
    }
}
