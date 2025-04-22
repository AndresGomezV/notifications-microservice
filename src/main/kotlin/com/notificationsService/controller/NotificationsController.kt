package com.notificationsService.controller

import com.notificationsService.dto.NotificationDTO
import com.notificationsService.model.Notification
import com.notificationsService.service.NotificationsService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/notifications")
class NotificationsController(private val notificationsService: NotificationsService) {

    @PostMapping
    fun createNotification(@RequestBody notificationDTO: NotificationDTO): ResponseEntity<Notification> {
        val notification = notificationsService.createNotification(notificationDTO)
        return ResponseEntity.ok(notification)
    }

    @GetMapping("/{userId}")
    fun getNotificationsByUserId(@PathVariable userId: Long): ResponseEntity<List<Notification>> {
        val notifications = notificationsService.getNotificationsByUserId(userId)
        return ResponseEntity.ok(notifications)
    }

    @PutMapping("/{notificationId}/read")
    fun markAsRead(@PathVariable notificationId: Long): ResponseEntity<Notification> {
        val notification = notificationsService.markAsRead(notificationId)
        return if (notification != null) ResponseEntity.ok(notification)
        else ResponseEntity.notFound().build()
    }



}