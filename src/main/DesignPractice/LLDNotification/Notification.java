package main.DesignPractice.LLDNotification;

import java.time.Instant;

public class Notification {
  private final String notificationId;
  private final int userId;
  private final String subject, message, eventType, idempotencyKey;
  private final Instant createdAt = Instant.now();
  private volatile NotificationStatus status = NotificationStatus.PENDING;

  public Notification(String id, NotificationRequest request) {
    notificationId = id;
    userId = request.getUserId();
    subject = request.getSubject();
    message = request.getMessage();
    eventType = request.getEventType();
    idempotencyKey = request.getIdempotencyKey();
  }

  public String getNotificationId() {
    return notificationId;
  }

  public int getUserId() {
    return userId;
  }

  public String getSubject() {
    return subject;
  }

  public String getMessage() {
    return message;
  }

  public String getEventType() {
    return eventType;
  }

  public String getIdempotencyKey() {
    return idempotencyKey;
  }

  public Instant getCreatedAt() {
    return createdAt;
  }

  public NotificationStatus getStatus() {
    return status;
  }

  public void setStatus(NotificationStatus status) {
    this.status = status;
  }
}
