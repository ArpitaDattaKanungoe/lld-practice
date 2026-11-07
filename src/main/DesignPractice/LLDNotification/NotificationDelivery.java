package main.DesignPractice.LLDNotification;

import java.time.Instant;

public class NotificationDelivery {
  private final String deliveryId, notificationId;
  private final ChannelType channelType;
  private volatile DeliveryStatus status = DeliveryStatus.PENDING;
  private volatile int retryCount;
  private volatile Instant nextRetryAt;
  private volatile String failureReason;

  public NotificationDelivery(String id, String notificationId, ChannelType channelType) {
    deliveryId = id;
    this.notificationId = notificationId;
    this.channelType = channelType;
  }

  public String getDeliveryId() {
    return deliveryId;
  }

  public String getNotificationId() {
    return notificationId;
  }

  public ChannelType getChannelType() {
    return channelType;
  }

  public DeliveryStatus getStatus() {
    return status;
  }

  public int getRetryCount() {
    return retryCount;
  }

  public Instant getNextRetryAt() {
    return nextRetryAt;
  }

  public String getFailureReason() {
    return failureReason;
  }

  public void setStatus(DeliveryStatus status) {
    this.status = status;
  }

  public void incrementRetryCount() {
    retryCount++;
  }

  public void setNextRetryAt(Instant at) {
    nextRetryAt = at;
  }

  public void setFailureReason(String reason) {
    failureReason = reason;
  }

  @Override
  public String toString() {
    return "Delivery{" + deliveryId + ", " + channelType + ", " + status + ", retries=" + retryCount
        + (failureReason == null ? "" : ", reason=" + failureReason) + "}";
  }
}
