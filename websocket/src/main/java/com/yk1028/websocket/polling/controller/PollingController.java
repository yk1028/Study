package com.yk1028.websocket.polling.controller;

import com.yk1028.websocket.domain.Message;
import com.yk1028.websocket.polling.service.PollingNotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PollingController {

    private PollingNotificationService pollingNotificationService;

    public PollingController(PollingNotificationService pollingNotificationService) {
        this.pollingNotificationService = pollingNotificationService;
    }

    @GetMapping("/polling/noti")
    public List<Message> poll() {
        return pollingNotificationService.getMessages();
    }

    @GetMapping("/polling/noti/{message}")
    public void sendNotification(@PathVariable String message) {
        pollingNotificationService.send(message);
    }
}
