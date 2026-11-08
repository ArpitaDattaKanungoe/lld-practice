package main.DesignPractice.AlertMonitorLLD;

import java.util.Optional;

public class AlertService {  // This handles the alert lifecycle.

  private AlertRepository alertRepository;

  public AlertService(AlertRepository alertRepository) {
    this.alertRepository = alertRepository;
  }

  public void processCondition(AlertRule rule, Metric metric, boolean violated) {

    Optional<Alert> existing = alertRepository.findActiveAlert(rule.getRuleId(), metric.getResourceId());

    if (violated) {
      if (existing.isPresent()) {
        return; // Avoid duplicate alert
      }
      Alert alert = new Alert(rule.getRuleId(), metric.getResourceId());
      alertRepository.save(alert);
      System.out.println("Alert created: " + alert.getAlertId());

    }
//    else {
//      if (existing.isPresent()) {
//        Alert alert = existing.get();
//        alert.resolve();
//        alertRepository.update(alert);
//        System.out.println("Alert resolved");
//      }
//    }
  }

  public void acknowledge(String alertId) {
    // In a complete implementation, fetch alert by ID,
    // acknowledge it and update it in the repository.
  }
}
