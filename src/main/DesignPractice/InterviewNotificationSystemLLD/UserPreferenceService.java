package main.DesignPractice.InterviewNotificationSystemLLD;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import main.DesignPractice.InterviewNotificationSystemLLD.model.ChannelType;
import main.DesignPractice.InterviewNotificationSystemLLD.model.UserPreference;

public class UserPreferenceService {
  private final Map<String, UserPreference> preferences = new ConcurrentHashMap<>(); // thread-safety

  public void savePreference(UserPreference preference) {
    preferences.put(preference.getUserId(), preference);
  }

  public UserPreference getPreference(String userId) {
    return preferences.getOrDefault(
        userId,
        new UserPreference(userId, Set.of(ChannelType.EMAIL))
    );
  }
}

