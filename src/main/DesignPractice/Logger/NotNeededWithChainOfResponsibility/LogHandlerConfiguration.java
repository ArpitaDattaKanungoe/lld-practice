package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.Appender;
import main.DesignPractice.Logger.LogLevel;

public class LogHandlerConfiguration {

  private static final LogHandler debug = new DebugHandler();
  private static final LogHandler info = new InfoHandler();
  private static final LogHandler warn = new WarnHandler();
  private static final LogHandler error = new ErrorHandler();

  public static LogHandler build(){
    debug.setNext(info);
    info.setNext(warn);
    warn.setNext(error);

    return debug;
  }

  public static void addAppenderForLevel(LogLevel level, Appender appender) {
    switch (level) {
      case Debug -> debug.subscribe(appender);
      case Info  -> info.subscribe(appender);
      case Warn  -> warn.subscribe(appender);
      case Error -> error.subscribe(appender);
    }
  }
}
