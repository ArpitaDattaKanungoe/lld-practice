package DesignPractice.Logger.WithChainOfResponsibility;

import DesignPractice.Logger.Appender;
import DesignPractice.Logger.LogLevel;

public class LogHandlerConfig {

  private static final LogHandler debug = new DebugHandler();
  private static final LogHandler info = new InfoHandler();
  private static final LogHandler warn = new WarnHandler();
  private static final LogHandler error = new ErrorHandler();


  public static LogHandler build() {
    debug.setNext(info);
    info.setNext(warn);
    warn.setNext(error);
    return debug;
  }

  public static void addAppendersToLevel(LogLevel logLevel, Appender appender) {
    switch (logLevel) {
      case Debug -> debug.subscribe(appender);
      case Info -> info.subscribe(appender);
      case Warn -> warn.subscribe(appender);
      case Error -> error.subscribe(appender);
    }
  }

}
