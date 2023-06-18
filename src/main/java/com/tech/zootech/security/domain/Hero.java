package com.tech.zootech.security.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Builder
@Entity
@Setter
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "heroes")
public class Hero extends AbstractEntity {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "city_name", nullable = false)
    private String city;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDateTime dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @ManyToOne
    @JoinColumn(name = "planet_id")
    private Planet planet;
}
