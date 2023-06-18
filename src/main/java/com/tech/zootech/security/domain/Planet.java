package com.tech.zootech.security.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "planets")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Planet extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planet")
    private List<Hero> heroes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "planet")
    private List<Continent> continents;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Planet planet = (Planet) o;
        return getId() != null && Objects.equals(getId(), planet.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
