package com.notificationsService.service

import com.notificationsService.dto.NotificationDTO
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component

@Component
@Qualifier("websocket")
class WebSocketSender(
    private val notificationsService: NotificationsService,
    private val messagingTemplate: SimpMessagingTemplate
) : MessageSender {


    override fun send(dto: NotificationDTO) {
        val savedNotification = notificationsService.createNotification(dto)

        val destination = "/user/${savedNotification.userId}/queue/notifications"
        messagingTemplate.convertAndSend(destination, savedNotification)
    }
}
