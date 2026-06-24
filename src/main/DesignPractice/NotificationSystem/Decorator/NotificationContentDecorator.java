package main.DesignPractice.NotificationSystem.Decorator;

import main.DesignPractice.NotificationSystem.NotificationContent;

public abstract class NotificationContentDecorator implements NotificationContent {

  protected NotificationContent notification;

  public NotificationContentDecorator(NotificationContent notification) {
    this.notification = notification;
  }

  // We don't implement getContent() here because every decorator changes the content differently.
  //But getUserId() doesn't change, so we simply delegate it.
  @Override
  public String getUserId() {
    return notification.getUserId();
  }
}
