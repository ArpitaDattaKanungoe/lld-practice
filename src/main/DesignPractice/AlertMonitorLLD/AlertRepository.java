package main.DesignPractice.AlertMonitorLLD;
import java.util.Optional;

public interface AlertRepository {

  Optional<Alert> findActiveAlert(String ruleId, String resourceId);

  void save(Alert alert);

  void update(Alert alert);
}
