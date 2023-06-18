package com.tech.zootech.chat.api;

import com.tech.zootech.chat.domain.entities.ChatMessage;
import com.tech.zootech.chat.repoitory.ChatMessageRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageController {
    ChatMessageRepository chatMessageRepository;

    @MessageMapping("/private-message")
    @SendToUser("/receive/private-message")
    public ResponseEntity<String> getPrivateMessage(final ChatMessage mess, final Principal principal) throws InterruptedException {
        Thread.sleep(1000);
        chatMessageRepository.save(mess);
        log.info("Received message private: " + principal.getName());
        return ResponseEntity.ok(HtmlUtils.htmlEscape("Sending personal message to user"+principal.getName()+": "+ mess.getMessageData().getContent()));
    }
}
