package com.tech.zootech.security.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@AllArgsConstructor
public class CargoShip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "cargoShip")
    @ToString.Exclude
    private List<Cargo> cargoList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CargoShip cargoShip = (CargoShip) o;
        return id != null && Objects.equals(id, cargoShip.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
