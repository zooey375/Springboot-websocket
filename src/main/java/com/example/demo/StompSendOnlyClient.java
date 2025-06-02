// 建立一個 STOMP over WebSocket 的客戶端

package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

// 這個應用程式是用來發送 STOMP 訊息的「純發送端」(Send only Client)。
public class StompSendOnlyClient {
	public static void main(String[] args) throws Exception {
        String url = "ws://localhost:8080/chat-websocket/websocket";
        WebSocketStompClient stompClient = new WebSocketStompClient(new StandardWebSocketClient());
        stompClient.setMessageConverter(new MappingJackson2MessageConverter()); //使用 Jackson 將 Java 物件轉換為 JSON 格式（因為 STOMP 通訊中常用 JSON 作為資料格式）。

        ListenableFuture<StompSession> future = stompClient.connect(url, new StompSessionHandlerAdapter() {});	//用 connect() 建立與 WebSocket 伺服器的連線。
        StompSession session = future.get(); // 等待連線完成

        for (int i = 1; i <= 10; i++) { // 發送 10 則JSON 格式的訊息到指定的 STOMP destination (/app/chat)。
            ChatMessage message = new ChatMessage();
            message.setFrom("AI");
            message.setContent("現在有大特價..." + i);
            session.send("/app/chat", message);
            System.out.println("訊息已發送: " + i);
            Thread.sleep(1000);
        }
        session.disconnect();
    }

}