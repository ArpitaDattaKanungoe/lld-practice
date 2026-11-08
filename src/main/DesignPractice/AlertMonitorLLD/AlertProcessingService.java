package main.DesignPractice.AlertMonitorLLD;

import java.util.List;

public class AlertProcessingService {
  private AlertRuleRepository ruleRepository;
  private AlertService alertService;

  public AlertProcessingService(AlertRuleRepository ruleRepository, AlertService alertService) {

    this.ruleRepository = ruleRepository;
    this.alertService = alertService;
  }

  // Main entry point
  public void process(Metric metric) {
    // 1. Fetch matching rules
    List<AlertRule> rules = ruleRepository.findEnabledRules(metric.getMetricName());

    // 2. Evaluate each rule
    for (AlertRule rule : rules) {
      boolean violated = rule.evaluate(metric.getValue());

      // 3. Manage alert lifecycle
      alertService.processCondition(rule, metric, violated);
    }
  }
}
