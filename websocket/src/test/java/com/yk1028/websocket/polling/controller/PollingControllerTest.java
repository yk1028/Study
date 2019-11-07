package com.yk1028.websocket.polling.controller;

import com.yk1028.websocket.domain.Message;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PollingControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    void poll() {
        String noti1 = "hi";
        String noti2 = "hi2";
        sendNotification(noti1);
        sendNotification(noti2);

        List<Message> messages = getMessages();
        assertThat(messages.size()).isEqualTo(2);
        assertThat(messages.get(0).getContents()).isEqualTo(noti1);
        assertThat(messages.get(1).getContents()).isEqualTo(noti2);

        List<Message> emptyMessages = getMessages();
        assertThat(emptyMessages.size()).isEqualTo(0);
    }

    private void sendNotification(String noti) {
        client.get().uri("/polling/noti/" + noti)
                .exchange()
                .expectStatus()
                .isOk();
    }

    private List<Message> getMessages() {
        return client.get().uri("/polling/noti")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Message.class)
                .returnResult()
                .getResponseBody();
    }
}