package com.tech.zootech.security.domain;

import com.tech.zootech.security.domain.enums.Color;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "unicorns")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Unicorn extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unicorn")
    private List<Droid> droids;

    @Column(name = "color")
    private Color color;

    public Unicorn(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Unicorn unicorn = (Unicorn) o;
        return getId() != null && Objects.equals(getId(), unicorn.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
