package com.notificationsService.service

import com.notificationsService.dto.NotificationDTO


interface MessageSender {
    fun send(dto: NotificationDTO)
}