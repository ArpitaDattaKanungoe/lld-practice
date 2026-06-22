package DesignPractice.Logger.WithChainOfResponsibility;

import DesignPractice.Logger.LogLevel;

public class InfoHandler extends LogHandler {

  @Override
  boolean canHandle(LogLevel logLevel) {
    return logLevel==LogLevel.Info;
  }
}
