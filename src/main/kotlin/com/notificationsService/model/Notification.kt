package com.notificationsService.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "notifications")
data class Notification(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    var senderUsername: String,

    @Column(nullable = false)
    var taskId: Long,

    @Column(nullable = false)
    var taskTitle: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var notificationType: NotificationType,

    @Column(nullable = false)
    var isRead: Boolean = false,

    @Column(nullable = false)
    val time: LocalDateTime = LocalDateTime.now()
)

enum class NotificationType {
    TASK_ACCEPTED, TASK_REJECTED, TASK_PENDING
}
