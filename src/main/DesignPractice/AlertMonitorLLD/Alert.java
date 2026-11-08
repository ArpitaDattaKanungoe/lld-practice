package main.DesignPractice.AlertMonitorLLD;

import java.time.Instant;
import java.util.UUID;

public class Alert {

  private String alertId;
  private String ruleId;
  private String resourceId;
  private AlertStatus status;
  private Instant createdAt;

  public Alert(String ruleId, String resourceId) {
    this.alertId = UUID.randomUUID().toString();
    this.ruleId = ruleId;
    this.resourceId = resourceId;
    this.status = AlertStatus.FIRING;
    this.createdAt = Instant.now();
  }

  public void acknowledge() {
    if (status == AlertStatus.FIRING) {
      status = AlertStatus.ACKNOWLEDGED;
    }
  }

  public void resolve() {
    status = AlertStatus.RESOLVED;
  }

  public String getAlertId() {
    return alertId;
  }

  public String getRuleId() {
    return ruleId;
  }

  public String getResourceId() {
    return resourceId;
  }

  public AlertStatus getStatus() {
    return status;
  }
}
