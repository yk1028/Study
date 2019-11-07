# Spring WebSocket

1. WebSocketMessageBrokerConfigurer 를 구현한 WebScoketConfig를 이용해 서버쪽에서 WebSocket을 받아들일 수 있도록 구현(메시지 큐 형태로 동작하는 브로커 생성)
    - WebSocketConfig.class 

2. 클라이언트 측에서 SockJs와 Stomp-websocket를 활용하여 서버와 연결(구독)

3. 서버측에서 연결시 사용한 destination(ex. "/topic/hello")를 이용해 클라이언트에게 메세지를 보낼 수 있다.
    - NotificationService.class
    

4. 데이터는 Json형식으로 주고 받는다.

# Polling

1. BlockingQueue를 활용하여 쉽게 구현가능

2. 즉각적인 응답이 필요하지 않을 때 polling을 고려해보면 좋을 것 같다. 
