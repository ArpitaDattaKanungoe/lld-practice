package main.DesignPractice.TaskExecution;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskRepository {

  private final Map<String, Task> taskMap =
      new ConcurrentHashMap<>();

  public void save(Task task) {

    taskMap.put(task.getTaskId(), task);
  }

  public Task findById(String taskId) {

    return taskMap.get(taskId);
  }

  public void update(Task task) {

    taskMap.put(task.getTaskId(), task);
  }
}