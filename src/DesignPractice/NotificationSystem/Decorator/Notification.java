package DesignPractice.NotificationSystem.Decorator;

import DesignPractice.NotificationSystem.NotificationContent;

public class Notification implements NotificationContent {

  private final String userId;
  private final String message;

  public Notification(String userId, String message) {
    this.userId = userId;
    this.message = message;
  }

  @Override
  public String getContent() {
    return message;
  }

  @Override
  public String getUserId() {
    return userId;
  }
}