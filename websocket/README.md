# Spring WebSocket

1. WebSocketMessageBrokerConfigurer 를 구현한 WebScoketConfig를 이용해 서버쪽에서 WebSocket을 받아들일 수 있도록 구현
    - WebSocketConfig.class 

2. 클라이언트 측에서 SockJs와 Stomp를 활용하여 서버와 연결(구독)

3. 서버측에서 연결시 사용한 destination(ex. "/topic/hello")를 이용해 클라이언트에게 메세지를 보낼 수 있다.
    - NotificationService.class
    
4. 데이터는 Json형식으로 주고 받는다.