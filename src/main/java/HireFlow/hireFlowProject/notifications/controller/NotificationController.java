package HireFlow.hireFlowProject.notifications.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import HireFlow.hireFlowProject.notifications.model.Notification;
import HireFlow.hireFlowProject.notifications.service.NotificationService;

@RestController
@RequestMapping("/api/notifications")
@PreAuthorize("isAuthenticated()")
public class NotificationController {

	private final NotificationService notificationService;

	public NotificationController(NotificationService notificationService) {

		this.notificationService = notificationService;
	}

	// Get My Notifications
	@GetMapping
	public List<Notification> getMyNotifications() {

		return notificationService.getMyNotifications();
	}

	// Get Unread Count
	@GetMapping("/unread-count")
	public Long getUnreadCount() {

		return notificationService.getUnreadCount();
	}

	// Mark Notification as Read
	@PutMapping("/{notificationId}/read")
	public Notification markAsRead(@PathVariable String notificationId) {

		return notificationService.markAsRead(notificationId);
	}

	@PutMapping("/read-all")
	public String markAllAsRead() {

		notificationService.markAllAsRead();

		return "All notifications marked as read.";
	}

	// Delete Notification
	@DeleteMapping("/{notificationId}")
	public String deleteNotification(@PathVariable String notificationId) {

		notificationService.deleteNotification(notificationId);

		return "Notification deleted successfully.";
	}

	@DeleteMapping
	public String deleteAllNotifications() {

		notificationService.deleteAllNotifications();

		return "All notifications deleted.";
	}

}