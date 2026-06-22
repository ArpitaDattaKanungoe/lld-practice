package DesignPractice.NotificationSystem;

import DesignPractice.NotificationSystem.Factory.ChannelType;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserPreferenceService {

  private final Map<String, UserPreferences> usePreferenceMap = new ConcurrentHashMap<>(); // for thread safety

  public void savePreferences(UserPreferences userPreferences) {
    usePreferenceMap.put(userPreferences.getUserId(), userPreferences);
  }

  public UserPreferences getPreference(String userId) {
return usePreferenceMap.get(userId);
  }
}
