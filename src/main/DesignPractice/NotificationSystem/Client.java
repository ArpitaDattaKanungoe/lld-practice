package main.DesignPractice.NotificationSystem;

import main.DesignPractice.NotificationSystem.Decorator.SignatureDecorator;
import main.DesignPractice.NotificationSystem.Decorator.Notification;
import main.DesignPractice.NotificationSystem.Decorator.TimestampDecorator;
import main.DesignPractice.NotificationSystem.Factory.ChannelType;
import main.DesignPractice.NotificationSystem.Observer.Logger;
import main.DesignPractice.NotificationSystem.Observer.NotificationEngine;
import main.DesignPractice.NotificationSystem.Observer.NotificationObservable;
import java.util.Set;

public class Client {

  public static void main(String[] args) {
    Notification notification = new Notification("Rama","I am miss Korea");
    NotificationContent content = new SignatureDecorator(new TimestampDecorator(notification),"ADK");

    NotificationObservable observable = new NotificationObservable();
    UserPreferenceService userPreferenceService = new UserPreferenceService();
    UserPreferences userPreferences = new UserPreferences("Sam", Set.of(ChannelType.SMS));
    userPreferenceService.savePreferences(userPreferences);
    NotificationService notificationService = new NotificationService(new NotificationDispatcher(userPreferenceService));
    Logger logger = new Logger();
    NotificationEngine engine = new NotificationEngine(notificationService);
    observable.addObserver(logger);
    observable.addObserver(engine);
    observable.setNotification(content);
  }
}
