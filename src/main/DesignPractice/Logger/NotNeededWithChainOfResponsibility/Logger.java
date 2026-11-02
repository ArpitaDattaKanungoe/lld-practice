package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.LogLevel;
import main.DesignPractice.Logger.LogMessage;

class Logger {
  private static final Logger INSTANCE = new Logger();

  private final LogHandler handlerChain;

  private Logger() {
    handlerChain = LogHandlerConfiguration.build();
  }

  public static Logger getInstance() {
    return INSTANCE;
  }

  public void log(LogLevel level, String message) {
    LogMessage msg = new LogMessage(level, message);
    handlerChain.handle(msg);
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
