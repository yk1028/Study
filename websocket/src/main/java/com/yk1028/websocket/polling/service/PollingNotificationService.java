package com.yk1028.websocket.polling.service;

import com.yk1028.websocket.domain.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Service
public class PollingNotificationService {

    private BlockingQueue<Message> messageBlockingQueue = new LinkedBlockingQueue<>();

    public void send(String notification){
        messageBlockingQueue.add(new Message(notification));
    }

    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        Message message;
        while ((message = messageBlockingQueue.poll()) != null){
            messages.add(message);
        }
        return messages;
    }
}
