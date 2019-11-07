package com.yk1028.websocket.controller;

import com.yk1028.websocket.domain.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageApiController {

    @MessageMapping("/hello")
    @SendTo("/topic/hello")
    public Message hello(Message message) throws Exception {
        return new Message("Hello " + message.getContents());
    }
}
