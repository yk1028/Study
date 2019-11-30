package com.yk1028.websocket.websocket.controller;

import com.yk1028.websocket.websocket.service.NotificationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationApiController {

    private NotificationService notificationService;

    public NotificationApiController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/noti/{message}")
    public void sendNotification(@PathVariable String message) {
        notificationService.sendToHello(message);
    }

    @GetMapping("/noti/{roomId}/{message}")
    public void sendNotification(@PathVariable Long roomId, @PathVariable String message) {
        notificationService.send(roomId, message);
    }
}
