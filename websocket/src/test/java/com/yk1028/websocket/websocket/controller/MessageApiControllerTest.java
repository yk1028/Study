package com.yk1028.websocket.websocket.controller;

import com.yk1028.websocket.domain.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageApiControllerTest {

    private static final String SUBSCRIBE_END_POINT = "/topic/hello";
    private static final String SEND_END_POINT = "/app/hello";

    @Value("${local.server.port}")
    private int port;
    private String URL;
    private CompletableFuture<Message> completableFuture;

    @BeforeEach
    void setUp() {
        completableFuture = new CompletableFuture<>();
        URL = "ws://localhost:" + port + "/websockethandler";
    }

    @Test
    void hello() throws Exception {
        //given
        WebSocketStompClient stompClient = new WebSocketStompClient(new SockJsClient(createTransportClient()));
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
        StompSession stompSession = stompClient
                .connect(URL, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
        stompSession.subscribe(SUBSCRIBE_END_POINT, new MessageStompFrameHandler());

        //when
        String contents = "user";
        stompSession.send(SEND_END_POINT, new Message(contents));

        //then
        Message helloMessage = completableFuture.get(5, SECONDS);
        assertThat(helloMessage.getContents()).isEqualTo("Hello " + contents);
    }

    private List<Transport> createTransportClient() {
        List<Transport> transports = new ArrayList<>(1);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        return transports;
    }

    private class MessageStompFrameHandler implements StompFrameHandler {
        @Override
        public Type getPayloadType(StompHeaders headers) {
            return Message.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            completableFuture.complete((Message) payload);
        }
    }
}