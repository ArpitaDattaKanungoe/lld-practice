package main.DesignPractice.AlertMonitorLLD;

public class AlertRule {

  private String ruleId;
  private String metricName;
  private double threshold;
  private ComparisonOperator operator;
  private long durationSeconds;

  public AlertRule(String ruleId, String metricName,
      double threshold, ComparisonOperator operator, long durationSeconds) {
    this.ruleId = ruleId;
    this.metricName = metricName;
    this.threshold = threshold;
    this.operator = operator;
    this.durationSeconds = durationSeconds;
  }

  public boolean evaluate(double value) {
    if (operator == ComparisonOperator.GREATER_THAN) {
      return value > threshold;
    }

    return value < threshold;
  }

  public String getRuleId() {
    return ruleId;
  }

  public String getMetricName() {
    return metricName;
  }

  public long getDurationSeconds() {
    return durationSeconds;
  }
}
