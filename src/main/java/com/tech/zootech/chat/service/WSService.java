package com.tech.zootech.chat.service;

import com.tech.zootech.chat.domain.entities.ChatMessage;

public interface WSService {

    void sendMessage(ChatMessage message);

    void sendPrivateMessage(ChatMessage message, Long id);

    void sendNotification(ChatMessage message);

    void sendPrivateNotification(ChatMessage message, Long id);
}
