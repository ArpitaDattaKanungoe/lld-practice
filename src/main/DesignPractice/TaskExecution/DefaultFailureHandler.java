package main.DesignPractice.TaskExecution;

public class DefaultFailureHandler implements FailureHandler {

  @Override
  public void handle(Task task, Exception error) {

    System.out.println(
        "Task permanently failed: " +
        task.getTaskId()
    );

    System.out.println(
        "Reason: " + error.getMessage()
    );

    // Production possibilities:
    // 1. Send an alert.
    // 2. Publish to a Dead Letter Queue.
    // 3. Notify the client.
  }
}
