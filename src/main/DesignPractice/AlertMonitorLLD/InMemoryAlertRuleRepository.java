package main.DesignPractice.AlertMonitorLLD;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryAlertRuleRepository implements AlertRuleRepository {
  private final Map<String, AlertRule> rules = new LinkedHashMap<>();

  @Override
  public void save(AlertRule rule) {
    rules.put(rule.getRuleId(), rule);
  }

  @Override
  public List<AlertRule> findEnabledRules(String metricName) {
    // All saved rules are enabled in this simple implementation.
    return rules.values().stream()
        .filter(rule -> rule.getMetricName().equals(metricName))
        .collect(Collectors.toList());
  }
}
