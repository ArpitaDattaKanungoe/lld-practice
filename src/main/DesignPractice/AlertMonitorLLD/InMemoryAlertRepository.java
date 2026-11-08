package main.DesignPractice.AlertMonitorLLD;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryAlertRepository implements AlertRepository {
  private final Map<String, Alert> alerts = new LinkedHashMap<>();

  @Override
  public Optional<Alert> findActiveAlert(String ruleId, String resourceId) {
    return alerts.values().stream()
        .filter(alert -> alert.getRuleId().equals(ruleId))
        .filter(alert -> alert.getResourceId().equals(resourceId))
        .filter(alert -> alert.getStatus() != AlertStatus.RESOLVED)
        .findFirst();
  }

  @Override
  public void save(Alert alert) {
    alerts.put(alert.getAlertId(), alert);
  }

  @Override
  public void update(Alert alert) {
    alerts.put(alert.getAlertId(), alert);
  }
}
