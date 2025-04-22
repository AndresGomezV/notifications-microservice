package com.notificationsService.service

import com.notificationsService.dto.NotificationDTO
import com.notificationsService.model.Notification
import com.notificationsService.repository.NotificationRepository
import org.springframework.stereotype.Service

@Service
class NotificationsService(private val notificationRepository: NotificationRepository) {

    fun createNotification(notificationDTO: NotificationDTO): Notification {
        val notification = Notification(
            id = null,
            userId = notificationDTO.userId,
            senderUsername = notificationDTO.senderUsername,
            taskId = notificationDTO.taskId,
            taskTitle = notificationDTO.taskTitle,
            notificationType = notificationDTO.notificationType
        )

        return notificationRepository.save(notification)
    }

    fun getNotificationsByUserId(userId: Long): List<Notification> {
        return notificationRepository.findAllByUserIdOrderByTimeDesc(userId)
    }

    fun markAsRead(notificationId: Long): Notification? {
        val notification = notificationRepository.findById(notificationId)
        return notification.orElse(null)?.let {
            it.isRead = true
            notificationRepository.save(it)
        }
    }
}