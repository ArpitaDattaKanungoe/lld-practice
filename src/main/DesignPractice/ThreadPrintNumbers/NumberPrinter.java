package main.DesignPractice.ThreadPrintNumbers;

class NumberPrinter {

  private int number = 1;
  private final int MAX = 100;

  public synchronized void printOdd() {

    while (number <= MAX) {

      while (number % 2 == 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }

      if (number <= MAX) {
        System.out.println(Thread.currentThread().getName() + " : " + number++);
      }

      notifyAll();
    }
  }

  public synchronized void printEven() {

    while (number <= MAX) {

      while (number % 2 != 0) {
        try {
          wait();
        } catch (InterruptedException e) {
          Thread.currentThread().interrupt();
        }
      }

      if (number <= MAX) {
        System.out.println(Thread.currentThread().getName() + " : " + number++);
      }

      notifyAll();
    }
  }
}
