package com.tech.zootech.chat.repoitory;

import com.tech.zootech.chat.domain.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
}
