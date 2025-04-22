package com.notificationsService.dto

import com.notificationsService.model.NotificationType
import java.io.Serializable

data class NotificationDTO(
    val userId: Long,
    val senderUsername: String,
    val taskId: Long,
    val taskTitle: String,
    val notificationType: NotificationType
) : Serializable
