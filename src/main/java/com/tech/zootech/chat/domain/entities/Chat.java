package com.tech.zootech.chat.domain.entities;

import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Chat extends AbstractEntity {
    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "chat")
    private List<ChatMessage> messages;
}
