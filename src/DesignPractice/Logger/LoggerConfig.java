package DesignPractice.Logger;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class LoggerConfig {

  private volatile LogLevel rootLevel;

  private final Map<LogLevel, List<Appender>> appenders = new ConcurrentHashMap<>();

  public LoggerConfig(LogLevel rootLevel) {
    this.rootLevel = rootLevel;
  }

  public LogLevel getRootLevel() {
    return rootLevel;
  }

  public void addAppender(LogLevel level, Appender appender) {
    appenders
        .computeIfAbsent(level, key -> new CopyOnWriteArrayList<>())
        .add(appender);
  }

  public List<Appender> getAppenders(LogLevel level) {
    return appenders.getOrDefault(level, Collections.emptyList());
  }

  public void setRootLevel(LogLevel rootLevel) {
    this.rootLevel = rootLevel;
  }
}
