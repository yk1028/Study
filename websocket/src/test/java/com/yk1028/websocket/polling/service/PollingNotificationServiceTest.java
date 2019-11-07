package com.yk1028.websocket.polling.service;

import com.yk1028.websocket.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PollingNotificationServiceTest {
    private static final Logger log = LoggerFactory.getLogger(PollingNotificationServiceTest.class);

    private PollingNotificationService pollingNotificationService;

    @BeforeEach
    void setUp() {
        pollingNotificationService = new PollingNotificationService();
    }

    @Test
    void notifyMessage() {
        pollingNotificationService.send("hi");
        pollingNotificationService.send("hihi");

        List<Message> messages = pollingNotificationService.getMessages();
        assertThat(messages.size()).isEqualTo(2);
        assertThat(messages.get(0).getContents()).isEqualTo("hi");
        assertThat(messages.get(1).getContents()).isEqualTo("hihi");
        assertThat(pollingNotificationService.getMessages().size()).isEqualTo(0);
    }
}