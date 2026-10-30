package main.DesignPractice.TaskExecution;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TaskFrameworkDemo {

  public static void main(String[] args) throws Exception {

    // 1. Create framework with 3 worker threads.
    TaskManager taskManager = new TaskManager(3);

    // 2. Configure retry policy.
    RetryPolicy retryPolicy = new RetryPolicy(
        3, Duration.ofSeconds(2), 2.0, Duration.ofSeconds(30), false
    );

    // 3. Create a task that fails twice and succeeds
    // on the third attempt.

    AtomicInteger counter = new AtomicInteger(0);

    Runnable action = () -> {

      int attempt = counter.incrementAndGet();

      System.out.println(
          "Executing task. Attempt: " + attempt
      );

      if (attempt < 3) {

        throw new RuntimeException(
            "Temporary service failure"
        );
      }

      System.out.println("Business operation successful!");
    };

    Task task = new Task(
        "T101",
        action,
        retryPolicy
    );

    // 4. Submit task.
    String taskId = taskManager.submit(task);

    System.out.println("Submitted: " + taskId);

    // 5. Track status.
    for (int i = 0; i < 15; i++) {

      System.out.println(
          "Current status: " +
          taskManager.getStatus(taskId)
      );

      if (taskManager.getStatus(taskId)
          == TaskStatus.COMPLETED) {
        break;
      }

      Thread.sleep(1000);
    }

    // 6. Shutdown framework.
    taskManager.shutdown();
  }
}
