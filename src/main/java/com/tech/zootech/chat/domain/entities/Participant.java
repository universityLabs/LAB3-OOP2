package com.tech.zootech.chat.domain.entities;

import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "participant")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Participant extends AbstractEntity {
    private String name;

    private String email;

    private String username;
}
