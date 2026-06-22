package DesignPractice.Logger.Client;

import DesignPractice.Logger.ConsoleAppender;
import DesignPractice.Logger.DefaultFormatter;
import DesignPractice.Logger.FileAppender;
import DesignPractice.Logger.Formatter;
import DesignPractice.Logger.LogLevel;
import DesignPractice.Logger.Logger2;
import DesignPractice.Logger.LoggerConfig;
import DesignPractice.Logger.LoggerManager2;

public class Client {

  public static void main(String[] args) {
//    LoggerManager manager = LoggerManager.getInstance();
//    LogHandlerConfig.addAppendersToLevel(LogLevel.Info, new ConsoleAppender(new DefaultFormatter()));
//    Logger logger = manager.getLogger(Client.class.getName(), LogLevel.Info);
//    logger.warn("hi");


    Formatter formatter = new DefaultFormatter();

    LoggerConfig config = new LoggerConfig(LogLevel.Warn);

    config.addAppender(LogLevel.Warn, new ConsoleAppender(formatter));

    config.addAppender(LogLevel.Error, new ConsoleAppender(formatter));

    config.addAppender(LogLevel.Error, new FileAppender(formatter));

    LoggerManager2.initialize(config);

    Logger2 logger = LoggerManager2.getInstance().getLogger("PaymentService");
    logger.warn("Lucifer");
  }
}
