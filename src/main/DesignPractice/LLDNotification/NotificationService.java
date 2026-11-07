package main.DesignPractice.LLDNotification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class NotificationService {
  private final PreferenceService preferences;
  private final NotificationRepository repository;
  private final NotificationQueue queue;

  public NotificationService(PreferenceService preferences, NotificationRepository repository,
      NotificationQueue queue) {
    this.preferences = preferences;
    this.repository = repository;
    this.queue = queue;
  }

  public Notification sendNotification(NotificationRequest request) throws InterruptedException {
    Optional<Notification> existing = repository.findByIdempotencyKey(request.getIdempotencyKey());
    if (existing.isPresent())
      return existing.get();

    Set<ChannelType> enabled = preferences.getEnabledChannels(request.getUserId());
    if (enabled.isEmpty())
      throw new IllegalStateException("No enabled channels for user " + request.getUserId());

    Notification n = new Notification(UUID.randomUUID().toString(), request);
    repository.saveNotification(n);
    List<NotificationDelivery> createdDeliveries = new ArrayList<>();
    // Persist every delivery before publishing any job. This prevents a fast
    // worker from calculating the parent status before all channels exist.
    for (ChannelType type : enabled) {
      NotificationDelivery d = new NotificationDelivery(UUID.randomUUID().toString(),
          n.getNotificationId(), type);
      repository.saveDelivery(d);
      createdDeliveries.add(d);
    }
    // Direct queue publishing. The recovery job handles a missed publish.
    for (NotificationDelivery d : createdDeliveries) {
      queue.publish(d.getDeliveryId());
    }
    return n;
  }
}
