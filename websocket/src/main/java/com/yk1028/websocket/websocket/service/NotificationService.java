package com.yk1028.websocket.websocket.service;

import com.yk1028.websocket.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendToHello(String notification) {
        this.template.convertAndSend("/topic/hello", new Message(notification));
    }

    public void send(Long roomId, String notification) {
        this.template.convertAndSend("/topic/" + roomId, new Message(notification));
    }
}
