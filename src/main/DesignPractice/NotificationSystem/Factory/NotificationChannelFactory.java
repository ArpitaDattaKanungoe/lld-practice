package main.DesignPractice.NotificationSystem.Factory;

import main.DesignPractice.NotificationSystem.Strategy.EmailChannel;
import main.DesignPractice.NotificationSystem.Strategy.NotificationChannel;
import main.DesignPractice.NotificationSystem.Strategy.SmsChannel;

public class NotificationChannelFactory {
  public static NotificationChannel getChannel(ChannelType channelType){
    return switch (channelType){
      case SMS -> new SmsChannel();
      case Email -> new EmailChannel();
      case WhatsApp -> throw new IllegalArgumentException("Unknown type");
    };
  }
}