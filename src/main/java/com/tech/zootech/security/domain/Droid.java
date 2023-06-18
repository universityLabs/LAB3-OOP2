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
@Setter
@Getter
@Table(name = "droids")
@AllArgsConstructor
@NoArgsConstructor
public class Droid extends AbstractEntity {
    @Column(name = "hp")
    private Integer hp;

    @Column(name = "attack_hp")
    private Integer attackHp;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unicorn_id")
    private Unicorn unicorn;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "droid")
    private List<Cupcake> cupcakes;

    @Column(name = "alive")
    private Boolean alive;

    public Droid(String name, Unicorn unicorn, Boolean alive) {
        this.name = name;
        this.unicorn = unicorn;
        this.alive = alive;
    }

    public Droid(String name, Boolean alive) {
        this.name = name;
        this.alive = alive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Droid droid = (Droid) o;
        return getId() != null && Objects.equals(getId(), droid.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
