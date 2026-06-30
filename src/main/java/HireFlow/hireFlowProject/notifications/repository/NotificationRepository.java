package HireFlow.hireFlowProject.notifications.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import HireFlow.hireFlowProject.notifications.dto.NotificationResponse;
import HireFlow.hireFlowProject.notifications.model.Notification;

public interface NotificationRepository extends MongoRepository<Notification, String> {

	List<Notification> findByRecipientEmailOrderByCreatedAtDesc(String recipientEmail);

	List<NotificationResponse> findByRecipientEmailAndIsReadFalseOrderByCreatedAtDesc(String recipientEmail);

	long countByRecipientEmailAndIsReadFalse(String recipientEmail);
}