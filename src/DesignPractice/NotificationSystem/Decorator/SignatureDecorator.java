package DesignPractice.NotificationSystem.Decorator;

import DesignPractice.NotificationSystem.NotificationContent;

public class SignatureDecorator extends NotificationContentDecorator {

  private String sign;

  public SignatureDecorator(NotificationContent notification, String sign) {
    super(notification);
    this.sign = sign;
  }

  @Override
  public String getContent() {
    return notification.getContent() + "\n-- " + (sign == null ? "" : sign);
  }

}
