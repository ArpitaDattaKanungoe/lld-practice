package main.DesignPractice.Logger;

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

    config.addAppender(LogLevel.Error, new FileAppender("async-demo.log",formatter));

    LoggerManager2.initialize(config); // if we don't initialize here, it will initialize defaultConfig ( error)

    Logger2 logger = LoggerManager2.getInstance().getLogger("PaymentService");
    logger.error("Lucifer");
    logger.warn("Dracula");
    logger.info("Reign");
  }
}
