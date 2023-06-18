package com.tech.zootech.customerservice.domain.entity;

import com.tech.zootech.customerservice.domain.enums.RegistrationStatus;
import com.tech.zootech.customerservice.domain.interfaces.IRegistrationHistory;
import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration_histories")
@EqualsAndHashCode
public class RegistrationHistory extends AbstractEntity implements IRegistrationHistory {
    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private RegistrationStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Customer customer;
}
