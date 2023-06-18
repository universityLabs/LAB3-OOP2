package com.tech.zootech.chat.config;

import com.sun.security.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class ClientHandshakeHandler extends DefaultHandshakeHandler {
    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
        final String randId = UUID.randomUUID().toString();
        log.info("{}", attributes.get("name"));
        log.info("User opened client unique ID {}, ipAddress {}", randId, request.getRemoteAddress());
        return new UserPrincipal(randId);
    }
}
