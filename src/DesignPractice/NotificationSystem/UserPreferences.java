package DesignPractice.NotificationSystem;

import DesignPractice.NotificationSystem.Factory.ChannelType;
import java.util.Set;

public class UserPreferences {
private String userId;
private Set<ChannelType> preferredChannel;

  public UserPreferences(String userId, Set<ChannelType> preferredChannel) {
    this.userId = userId;
    this.preferredChannel = preferredChannel;
  }

  public String getUserId() {
    return userId;
  }

  public Set<ChannelType> getPreferredChannel() {
    return preferredChannel;
  }
}
