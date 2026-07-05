package main.DesignPractice.InterviewNotificationSystemLLD.factory;

import main.DesignPractice.InterviewNotificationSystemLLD.model.ChannelType;
import main.DesignPractice.InterviewNotificationSystemLLD.strategy.EmailNotificationChannel;
import main.DesignPractice.InterviewNotificationSystemLLD.strategy.NotificationChannel;
import main.DesignPractice.InterviewNotificationSystemLLD.strategy.PushNotificationChannel;
import main.DesignPractice.InterviewNotificationSystemLLD.strategy.SmsNotificationChannel;

public class NotificationChannelFactory {
  public static NotificationChannel getChannel(ChannelType channelType){
    return switch (channelType) {
      case EMAIL -> new EmailNotificationChannel();
      case SMS -> new SmsNotificationChannel();
      case PUSH -> new PushNotificationChannel();
    };
  }
}