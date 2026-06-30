package HireFlow.hireFlowProject.notifications.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import HireFlow.hireFlowProject.notifications.dto.NotificationResponse;
import HireFlow.hireFlowProject.notifications.model.Notification;
import HireFlow.hireFlowProject.notifications.repository.NotificationRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Service
public class NotificationService {

	private final SimpMessagingTemplate messagingTemplate;
	private final NotificationRepository notificationRepo;

	public NotificationService(NotificationRepository notificationRepo, SimpMessagingTemplate messagingTemplate) {

		this.notificationRepo = notificationRepo;
		this.messagingTemplate = messagingTemplate;
	}

	// Create Notification
	public Notification createNotification(String recipientEmail, String title, String message, String type,
			String referenceId, String referenceType, String actionUrl) {

		Notification notification = new Notification();
		System.out.println("========== Creating Notification =========="); // debug pointers
		notification.setRecipientEmail(recipientEmail);
		notification.setTitle(title);
		notification.setMessage(message);
		notification.setType(type);
		switch (type) {

		case "APPLICATION":
			notification.setIcon("work");
			notification.setPriority("MEDIUM");
			break;

		case "INTERVIEW":
			notification.setIcon("calendar_month");
			notification.setPriority("HIGH");
			break;

		case "JOB":
			notification.setIcon("business_center");
			notification.setPriority("LOW");
			break;

		case "SYSTEM":
			notification.setIcon("settings");
			notification.setPriority("LOW");
			break;

		default:
			notification.setIcon("notifications");
			notification.setPriority("LOW");
		}
		notification.setReferenceId(referenceId);
		notification.setReferenceType(referenceType);
		notification.setActionUrl(actionUrl);
		notification.setIsRead(false);
//		notification.setReadAt(LocalDateTime.now());
		notification.setCreatedAt(LocalDateTime.now());

		System.out.println(notification); // debug pointers

		Notification saved = notificationRepo.save(notification);

		NotificationResponse dto = new NotificationResponse();

		dto.setId(saved.getId());
		dto.setTitle(saved.getTitle());
		dto.setMessage(saved.getMessage());
		dto.setType(saved.getType());
		dto.setActionUrl(saved.getActionUrl());
		dto.setPriority(saved.getPriority());
		dto.setIcon(saved.getIcon());
		dto.setCreatedAt(saved.getCreatedAt());
		dto.setIsRead(saved.getIsRead());
		dto.setReadAt(saved.getReadAt());

		messagingTemplate.convertAndSendToUser(saved.getRecipientEmail(), "/queue/notifications", dto);

		return saved;
	}

	// Get My Notifications
	public List<Notification> getMyNotifications() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return notificationRepo.findByRecipientEmailOrderByCreatedAtDesc(email);
	}

	// Unread Count
	public long getUnreadCount() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		return notificationRepo.countByRecipientEmailAndIsReadFalse(email);
	}

	// Mark as Read
	public Notification markAsRead(String notificationId) {

		Notification notification = notificationRepo.findById(notificationId)
				.orElseThrow(() -> new RuntimeException("Notification not found"));

		notification.setIsRead(true);
		notification.setReadAt(LocalDateTime.now());
		return notificationRepo.save(notification);
	}

	public void markAllAsRead() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		List<Notification> notifications = notificationRepo.findByRecipientEmailOrderByCreatedAtDesc(email);

		for (Notification notification : notifications) {

			notification.setIsRead(true);
			notification.setReadAt(LocalDateTime.now());
		}

		notificationRepo.saveAll(notifications);
	}

	// Delete Notification
	public void deleteNotification(String notificationId) {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		Notification notification = notificationRepo.findById(notificationId)
				.orElseThrow(() -> new RuntimeException("Notification not found"));

		if (!notification.getRecipientEmail().equals(email)) {
			throw new RuntimeException("Access denied");
		}

		notificationRepo.delete(notification);
	}

	public void deleteAllNotifications() {

		String email = SecurityContextHolder.getContext().getAuthentication().getName();

		List<Notification> notifications = notificationRepo.findByRecipientEmailOrderByCreatedAtDesc(email);

		notificationRepo.deleteAll(notifications);
	}
}