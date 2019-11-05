package com.yk1028.aop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LoggingControllerTest {

    @Autowired
    private WebTestClient client;

    @Test
    void logging() {
        client.get().uri("/hello")
                .exchange()
                .expectStatus()
                .isOk();
    }

    @Test
    void timer() {
        client.get().uri("/hello2")
                .exchange()
                .expectStatus()
                .isOk();
    }
}