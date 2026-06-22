package DesignPractice.NotificationSystem.Observer;

import DesignPractice.NotificationSystem.Decorator.Notification;
import DesignPractice.NotificationSystem.NotificationContent;
import java.util.ArrayList;
import java.util.List;

public class NotificationObservable implements Observable {

  private List<Observer> subscriberList = new ArrayList<>();
  private NotificationContent currentNotification;

  @Override
  public void addObserver(Observer subscriber) {
    subscriberList.add(subscriber);
  }

  @Override
  public void removeObserver(Observer subscriber) {
    subscriberList.remove(subscriber);
  }

  private void notifyObservers() {
    for (Observer subscriber : subscriberList) {
      subscriber.update(currentNotification);
    }
  }

 public void setNotification(NotificationContent notification) {
    this.currentNotification = notification;
    notifyObservers();
  }

  public NotificationContent getNotification() {
    return currentNotification;
  }

  @Override
  public String getNotificationContent() {
    return currentNotification.getContent();
  }

}

