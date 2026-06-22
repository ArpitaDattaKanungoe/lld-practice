package DesignPractice.NotificationSystem.Observer;

public interface Observable {
  void addObserver(Observer subscriber);
  void removeObserver(Observer subscriber);
//  void notifyObservers();
  String getNotificationContent();
}
