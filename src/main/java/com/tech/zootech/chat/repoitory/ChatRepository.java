package com.tech.zootech.chat.repoitory;


import com.tech.zootech.chat.domain.entities.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
