package main.DesignPractice.LLDNotification;

import java.util.Objects;

public class NotificationRequest {
  private final int userId;
  private final String subject, message, eventType, idempotencyKey;

  public NotificationRequest(int userId, String subject, String message, String eventType,
      String idempotencyKey) {
    this.userId = userId;
    this.subject = Objects.requireNonNull(subject);
    this.message = Objects.requireNonNull(message);
    this.eventType = Objects.requireNonNull(eventType);
    this.idempotencyKey = Objects.requireNonNull(idempotencyKey);
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
}
