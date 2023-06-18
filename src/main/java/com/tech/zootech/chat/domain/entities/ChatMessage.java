package com.tech.zootech.chat.domain.entities;

import com.tech.zootech.chat.domain.data.MessageData;
import com.tech.zootech.chat.domain.enums.MessageStatus;
import com.tech.zootech.security.domain.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "chat_message")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ChatMessage extends AbstractEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Chat chat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Participant sender;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Participant receiver;

    @Embedded
    private MessageData messageData;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MessageStatus status;
}
