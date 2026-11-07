package main.DesignPractice.LLDNotification;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryNotificationRepository implements NotificationRepository {
  private final Map<String, Notification> notifications = new ConcurrentHashMap<>();
  private final Map<String, NotificationDelivery> deliveries = new ConcurrentHashMap<>();
  private final Map<String, String> idempotency = new ConcurrentHashMap<>();

  public void saveNotification(Notification n) {
    String existing = idempotency.putIfAbsent(n.getIdempotencyKey(), n.getNotificationId());
    if (existing != null && !existing.equals(n.getNotificationId()))
      throw new IllegalStateException("Duplicate idempotency key: " + n.getIdempotencyKey());
    notifications.putIfAbsent(n.getNotificationId(), n);
  }

  public Optional<Notification> getNotification(String id) {
    return Optional.ofNullable(notifications.get(id));
  }

  public Optional<Notification> findByIdempotencyKey(String key) {
    String id = idempotency.get(key);
    return id == null ? Optional.empty() : getNotification(id);
  }

  public void saveDelivery(NotificationDelivery d) {
    deliveries.putIfAbsent(d.getDeliveryId(), d);
  }

  public Optional<NotificationDelivery> getDelivery(String id) {
    return Optional.ofNullable(deliveries.get(id));
  }

  public List<NotificationDelivery> getDeliveries(String notificationId) {
    return deliveries.values().stream().filter(d -> d.getNotificationId().equals(notificationId))
        .collect(Collectors.toList());
  }

  public Optional<NotificationDelivery> claimDelivery(String id) {
    final NotificationDelivery[] claimed = new NotificationDelivery[1];
    deliveries.computeIfPresent(id, (key, d) -> {
      boolean pending = d.getStatus() == DeliveryStatus.PENDING;
      boolean dueRetry = d.getStatus() == DeliveryStatus.RETRY_SCHEDULED
          && d.getNextRetryAt() != null && !d.getNextRetryAt().isAfter(Instant.now());
      if (pending || dueRetry) {
        d.setStatus(DeliveryStatus.PROCESSING);
        claimed[0] = d;
      }
      return d;
    });
    return Optional.ofNullable(claimed[0]);
  }

  public List<NotificationDelivery> findRecoverableDeliveries(Instant now) {
    return deliveries.values().stream()
        .filter(d -> d.getStatus() == DeliveryStatus.PENDING
            || (d.getStatus() == DeliveryStatus.RETRY_SCHEDULED && d.getNextRetryAt() != null
                && !d.getNextRetryAt().isAfter(now)))
        .collect(Collectors.toList());
  }

  public void updateNotificationStatus(String id, NotificationStatus status) {
    Notification n = notifications.get(id);
    if (n != null)
      n.setStatus(status);
  }
}
