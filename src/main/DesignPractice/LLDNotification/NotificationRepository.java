package main.DesignPractice.LLDNotification;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface NotificationRepository {
  void saveNotification(Notification notification);

  Optional<Notification> getNotification(String id);

  Optional<Notification> findByIdempotencyKey(String key);

  void saveDelivery(NotificationDelivery delivery);

  Optional<NotificationDelivery> getDelivery(String id);

  List<NotificationDelivery> getDeliveries(String notificationId);

  Optional<NotificationDelivery> claimDelivery(String deliveryId);

  List<NotificationDelivery> findRecoverableDeliveries(Instant now);

  void updateNotificationStatus(String notificationId, NotificationStatus status);
}
