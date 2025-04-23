package com.notificationsService.jms

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.notificationsService.dto.NotificationDTO
import com.notificationsService.service.NotificationsService
import jakarta.jms.Message
import jakarta.jms.TextMessage
import org.springframework.jms.annotation.JmsListener
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
class NotificationListener(private val notificationsService: NotificationsService,
    private val messagingTemplate: SimpMessagingTemplate) {

    private val objectMapper = jacksonObjectMapper()

    @JmsListener(destination = "notification-queue")
    fun receiveMessage(message: Message) {
        try {
            val textMessage = message as TextMessage
            val json = textMessage.text
            val dto: NotificationDTO = objectMapper.readValue(json)

            println("JSON received and parsed: $dto")
            val savedNotification = notificationsService.createNotification(dto)

            //WebSocket
            val destination = "/user/${savedNotification.userId}/queue/notifications"
            messagingTemplate.convertAndSend(destination, savedNotification)

        } catch (ex: Exception) {
            println("Error processing JSON: ${ex.message}")
            ex.printStackTrace()
        }
    }
}