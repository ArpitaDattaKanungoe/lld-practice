package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.LogLevel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoggerManager {
  private static final LoggerManager INSTANCE = new LoggerManager();
  private final Map<String, Logger> loggers;

  public LoggerManager() {
    loggers = new ConcurrentHashMap<>();
  }

  public static LoggerManager getInstance() {
    return INSTANCE;
  }
  // Build handler chain only once
  private final LogHandler rootHandler = LogHandlerConfig.build();

  public Logger getLogger(String className, LogLevel rootLevel) {
    return loggers.computeIfAbsent(className,
        key -> new Logger(key,rootLevel,rootHandler));
  }
}