package main.DesignPractice.TaskExecution;

public interface FailureHandler {

  void handle(Task task, Exception error);
}
