package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.Appender;
import main.DesignPractice.Logger.LogLevel;
import main.DesignPractice.Logger.LogMessage;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class LogHandler {
  protected LogHandler next;
  final List<Appender> appenders = new CopyOnWriteArrayList<>();

  public void subscribe(Appender appender) {
    appenders.add(appender);
  }

  public void notifyObserver(LogMessage message) {
    for (Appender appender : appenders) {
      appender.append(message);
    }
  }

  abstract boolean canHandle(LogLevel logLevel);

  public void handle(LogMessage message) {
    if (canHandle(message.getLogLevel())) {
      notifyObserver(message);
    } else if (next != null) {
      next.handle(message);
    }
  }

  public void setNext(LogHandler next) {
    this.next = next;
  }
}
