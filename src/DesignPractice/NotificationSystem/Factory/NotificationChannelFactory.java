package DesignPractice.NotificationSystem.Factory;

import DesignPractice.NotificationSystem.Strategy.EmailChannel;
import DesignPractice.NotificationSystem.Strategy.NotificationChannel;
import DesignPractice.NotificationSystem.Strategy.SmsChannel;

public class NotificationChannelFactory {
  public static NotificationChannel getChannel(ChannelType channelType){
    return switch (channelType){
      case SMS -> new SmsChannel();
      case Email -> new EmailChannel();
      case WhatsApp -> throw new IllegalArgumentException("Unknown type");
    };
  }
}