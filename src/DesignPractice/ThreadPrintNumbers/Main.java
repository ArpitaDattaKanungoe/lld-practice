package DesignPractice.ThreadPrintNumbers;

public class Main {

  public static void main(String[] args) {

    NumberPrinter printer = new NumberPrinter();

    Thread oddThread = new Thread(printer::printOdd, "Odd Thread");
    Thread evenThread = new Thread(printer::printEven, "Even Thread");

    oddThread.start();
    evenThread.start();
  }
}
