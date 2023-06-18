package com.tech.zootech.security.domain;

import com.tech.zootech.security.domain.enums.Filling;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "cupcakes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cupcake extends AbstractEntity {
    @Column(name = "filling")
    private Filling filling;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "droid_id")
    private Droid droid;

    public Cupcake(Filling filling) {
        this.filling = filling;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Cupcake cupcake = (Cupcake) o;
        return getId() != null && Objects.equals(getId(), cupcake.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
