package com.notificationsService.jms

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.notificationsService.dto.NotificationDTO
import com.notificationsService.service.NotificationsService
import jakarta.jms.Message
import jakarta.jms.ObjectMessage
import jakarta.jms.TextMessage
import org.springframework.jms.annotation.JmsListener
import org.springframework.stereotype.Component

@Component
class NotificationListener(private val notificationsService: NotificationsService) {

    @JmsListener(destination = "notification-queue")
    fun receiveMessage(message: Message) {
        try {
            val textMessage = message as TextMessage
            val json = textMessage.text

            val objectMapper = jacksonObjectMapper()
            val dto: NotificationDTO = objectMapper.readValue(json)

            println("✅ JSON recibido y parseado: $dto")
            notificationsService.createNotification(dto)

        } catch (ex: Exception) {
            println("❌ ERROR procesando mensaje JSON: ${ex.message}")
            ex.printStackTrace()
        }
    }
}