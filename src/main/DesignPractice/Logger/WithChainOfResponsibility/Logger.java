package main.DesignPractice.Logger.WithChainOfResponsibility;

import main.DesignPractice.Logger.LogLevel;
import main.DesignPractice.Logger.LogMessage;

public class Logger {

  private final String name;
  private final LogHandler handler;
  private final LogLevel rootLevel;

  public Logger(String name, LogLevel rootLevel, LogHandler handler) {
    this.name = name;
    this.rootLevel = rootLevel;
    this.handler = handler;;
  }

  private void log(LogLevel logLevel, String message) {
    // Ignore messages below configured level
    if (logLevel.ordinal() < rootLevel.ordinal()) {
      return;
    }
    LogMessage msg = new LogMessage(logLevel, message, name);
    handler.handle(msg);
  }

  public void debug(String message) {
    log(LogLevel.Debug, message);
  }

  public void info(String message) {
    log(LogLevel.Info, message);
  }

  public void warn(String message) {
    log(LogLevel.Warn, message);
  }

  public void error(String message) {
    log(LogLevel.Error, message);
  }
}
