package main.DesignPractice.TaskExecution;

public enum TaskStatus {
  SUBMITTED,
  RUNNING,
  RETRY_SCHEDULED,
  COMPLETED,
  EXHAUSTED,
  CANCELLED
}
