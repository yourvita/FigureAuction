# 굿즈모아

----
### 굿즈, 피규어 판매 경매 서비스
### 웹소켓을 이용한 실시간 알림, 1:1채팅

- 경매 서비스
  + 상품 등록
    + ![상품등록](https://github.com/user-attachments/assets/652051ed-16c2-4c75-a2bb-b663ce91f381)
  + 상품 등록 후 상품 상세
    + ![상품상세](https://github.com/user-attachments/assets/1e626c1d-9d75-4849-bc75-39097fe3eac4)
  + 경매 진행중인 상품 목록
    + ![경매진행중상품목록](https://github.com/user-attachments/assets/7370d59d-7b10-4f8d-ae02-ea3beb67356c)
  + 입찰
    + ![입찰상세](https://github.com/user-attachments/assets/9f6e4316-0cd3-4884-b704-d78bbeabb9e2)
### **웹소켓이용 기능** 
  + 상회 입찰시 알림 발송  
  ![상회입찰시알림](https://github.com/user-attachments/assets/9e6512df-6f52-4815-a184-ac59dedd4852)
```  
// 클라이언트에 알림 발송       
      public void sendBidOvertakenNotification(Long userId, String message) {
      messagingTemplate.convertAndSend("/topic/notify/" + userId, message);
  }
```
```
  // 웹소켓 config
  @Override
      public void configureMessageBroker(MessageBrokerRegistry config) {
      config.enableSimpleBroker("/topic", "/queue", "/user");
      config.setApplicationDestinationPrefixes("/app");
  }

  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/ws-notify")
      .setAllowedOriginPatterns("*").withSockJS();
  }
  ```
```
window.addEventListener('DOMContentLoaded', function()
        {
            const socket = new SockJS("/ws-notify");
            const stompClient = Stomp.over(socket);

            const userId = [[${session.userId}]]; // 로그인된 사용자 ID
            stompClient.connect({}, function() {
                console.log("stomp 연결 성공")
                stompClient.subscribe("/topic/notify/" + userId, function(notification) {
                    // 알림 표시
                    console.log("콘솔: "+notification.body)
                    alert("알림" + notification.body);
                    // location.reload(true);
                });
            }, function(error) {
                console.log("stomp 연결실패")
            })
        });
```
  + 1:1 실시간 채팅
```
로그인된 사용자와 상품을 등록한 사용자가 같은지 다른지에 따라
요청 파라미터에 값을 나누어준다.
<td>
    <button class="btn btn-primary" style="width:80%;"
            th:onclick="|location.href='@{/user/dm/{targetEmail}(targetEmail=${dmroom.senderEmail == session.userEmail ? dmroom.recipientEmail : dmroom.senderEmail})}'|">
                JOIN
    </button>
</td>
<td>
    <button class="btn btn-primary" style="width:80%;" th:onclick="|location.href='@{/user/deleteDm/{targetEmail}(targetEmail=${dmroom.senderEmail == session.userEmail ? dmroom.recipientEmail : dmroom.senderEmail})}'|">DELETE</button>
</td>
```
  + 관리자 기능
    - 관리자는 부적절한 상품을 삭제할 수 있습니다.
    - ![관리자상품삭제](https://github.com/user-attachments/assets/2b2a3fb5-18e3-4bcd-8b43-36459706080a)
    - 관리자는 회원을 검색 및 등급조정할 수 있습니다.
    - ![관리자 회원관리](https://github.com/user-attachments/assets/af473774-567f-4200-9d45-03e8400c615e)
