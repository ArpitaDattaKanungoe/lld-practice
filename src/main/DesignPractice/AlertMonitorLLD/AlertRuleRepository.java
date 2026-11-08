package main.DesignPractice.AlertMonitorLLD;

import java.util.List;

public interface AlertRuleRepository {

  void save(AlertRule rule);

  List<AlertRule> findEnabledRules(String metricName);
}
