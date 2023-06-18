package com.tech.zootech.chat.service.impl;

import com.tech.zootech.chat.domain.entities.ChatMessage;
import com.tech.zootech.chat.service.WSService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WSServiceImpl implements WSService {
    private final SimpMessagingTemplate messagingTemplate;

    @Override
    public void sendMessage(ChatMessage message) {
        messagingTemplate.convertAndSend("/receive/message",message.getMessageData());
    }

    @Override
    public void sendPrivateMessage(ChatMessage message, Long id) {
        messagingTemplate.convertAndSendToUser(String.valueOf(id),"/receive/private-message", message.getMessageData());
    }

    @Override
    public void sendNotification(ChatMessage message) {
        messagingTemplate.convertAndSend("/receive/global-notification",message.getMessageData());
    }

    @Override
    public void sendPrivateNotification(ChatMessage message, Long id) {
        messagingTemplate.convertAndSendToUser(String.valueOf(id),"/receive/private-notification",message.getMessageData());
    }
}
