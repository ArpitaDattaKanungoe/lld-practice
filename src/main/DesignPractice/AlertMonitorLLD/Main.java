package main.DesignPractice.AlertMonitorLLD;

public class Main {

  public static void main(String[] args) {
    AlertRuleRepository ruleRepository = new InMemoryAlertRuleRepository();
    AlertRepository alertRepository = new InMemoryAlertRepository();

    // Register the monitoring rule once.
    ruleRepository.save(new AlertRule(
        "rule-1", "cpu_usage", 80, ComparisonOperator.GREATER_THAN, 0));

    AlertService alertService = new AlertService(alertRepository);
    AlertProcessingService processingService = new AlertProcessingService(ruleRepository, alertService);

    // High CPU creates an alert.
    processingService.process(new Metric("cpu_usage", 90, "server-1"));
    alertRepository.findActiveAlert("rule-1", "server-1")
        .ifPresent(alert -> System.out.println(alert.getStatus())); // FIRING
/*
    // Normal CPU resolves the alert.
    evaluator.process(new Metric("cpu_usage", 50, "server-1"));
    System.out.println(alertRepository.findActiveAlert("rule-1", "server-1")
        .map(alert -> alert.getStatus().name())
        .orElse("No active alert"));
 */
  }
}
