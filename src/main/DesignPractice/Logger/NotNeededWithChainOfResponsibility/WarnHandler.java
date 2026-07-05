package main.DesignPractice.Logger.NotNeededWithChainOfResponsibility;

import main.DesignPractice.Logger.LogLevel;

public class WarnHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel == LogLevel.Warn;
  }
}
