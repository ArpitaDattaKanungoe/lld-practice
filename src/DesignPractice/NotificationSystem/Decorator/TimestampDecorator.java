package DesignPractice.NotificationSystem.Decorator;

import DesignPractice.NotificationSystem.NotificationContent;
import java.time.Instant;

public class TimestampDecorator extends NotificationContentDecorator {

  public TimestampDecorator(NotificationContent notification) {
    super(notification);
  }

  @Override
  public String getContent() {
    return Instant.now() + " | " + notification.getContent();
  }
}
