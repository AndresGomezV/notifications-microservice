package com.notificationsService.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig: WebSocketMessageBrokerConfigurer {

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        // Prefijos para destinos que pueden recibir los clientes
        registry.enableSimpleBroker("/topic", "/queue", "/user")
        registry.setUserDestinationPrefix("/user")
    }

    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        // Punto donde se conecta el frontend (WebSocket handshake)
        registry.addEndpoint("/ws")
            .setAllowedOriginPatterns("*") // Abierto para desarrollo
            .withSockJS() // Soporte para navegadores antiguos
    }
}