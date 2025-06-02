package com.example.demo;

import java.net.http.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//WebSocket 的資訊配置
@Configuration
@EnableWebSocketMessageBroker // 啟用STOMP
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

		// 定義訊息路由規則
}
