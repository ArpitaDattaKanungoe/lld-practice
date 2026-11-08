package main.DesignPractice.AlertMonitorLLD;
import java.time.Instant;

public class Metric {

  private String metricName;
  private double value;
  private String resourceId;
  private Instant timestamp;

  public Metric(String metricName, double value, String resourceId) {
    this.metricName = metricName;
    this.value = value;
    this.resourceId = resourceId;
    this.timestamp = Instant.now();
  }

  public String getMetricName() {
    return metricName;
  }

  public double getValue() {
    return value;
  }

  public String getResourceId() {
    return resourceId;
  }

  public Instant getTimestamp() {
    return timestamp;
  }
}
