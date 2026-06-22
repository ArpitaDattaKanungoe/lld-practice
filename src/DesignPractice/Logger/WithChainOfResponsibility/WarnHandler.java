package DesignPractice.Logger.WithChainOfResponsibility;

import DesignPractice.Logger.LogLevel;

public class WarnHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel == LogLevel.Warn;
  }
}
