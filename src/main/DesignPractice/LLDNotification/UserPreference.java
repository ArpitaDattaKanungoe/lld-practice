package main.DesignPractice.LLDNotification;

import java.util.Set;

public class UserPreference {
  private final int userId;
  private final Set<ChannelType> enabledChannels;

  public UserPreference(int userId, Set<ChannelType> channels) {
    this.userId = userId;
    enabledChannels = Set.copyOf(channels);
  }

  public int getUserId() {
    return userId;
  }

  public Set<ChannelType> getEnabledChannels() {
    return enabledChannels;
  }

  public boolean isEnabled(ChannelType channel) {
    return enabledChannels.contains(channel);
  }
}
