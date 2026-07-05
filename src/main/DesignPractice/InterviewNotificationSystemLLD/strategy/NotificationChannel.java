package main.DesignPractice.InterviewNotificationSystemLLD.strategy;


import main.DesignPractice.InterviewNotificationSystemLLD.model.Notification;

public interface NotificationChannel {
 void send(Notification notification);
}
