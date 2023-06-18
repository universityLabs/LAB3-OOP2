package com.tech.zootech.chat.api;

import com.tech.zootech.chat.domain.entities.ChatMessage;
import com.tech.zootech.chat.service.WSService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dev")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MessageController {
    WSService wsService;

    @PostMapping("/send-message")
    public void sendMessage(@RequestBody ChatMessage message){
        wsService.sendMessage(message);
    }

    @PostMapping("/send-private-message/{id}")
    public void sendPrivateMessage(@RequestBody ChatMessage message, @PathVariable Long id){
        wsService.sendPrivateMessage(message, id);
    }

    @PostMapping(value = "/send-notification-global")
    public void sendNotification(@RequestBody ChatMessage message){
        wsService.sendNotification(message);
    }

    @PostMapping(value = "/send-notification-private/{id}")
    public void sendPrivateNotification(@RequestBody ChatMessage message, @PathVariable Long id){
        wsService.sendPrivateNotification(message, id);
    }
}
