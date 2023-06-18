package com.tech.zootech.customerservice.domain.data;

import com.tech.zootech.customerservice.domain.interfaces.ICustomer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRegistrationData implements ICustomer {
    @NotNull(message = "Id providing from front-end is necessary")
    private Long id;
    private String firstName;
    private String lastName;
    @Email
    private String email;
}
