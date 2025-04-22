package com.notificationsService.repository

import com.notificationsService.model.Notification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface NotificationRepository : JpaRepository<Notification, Long> {

    fun findAllByUserIdOrderByTimeDesc(userId: Long): List<Notification>

    fun countByUserIdAndIsReadFalse(userId: Long): Long


}