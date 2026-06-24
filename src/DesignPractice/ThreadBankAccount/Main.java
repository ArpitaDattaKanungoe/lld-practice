package DesignPractice.ThreadBankAccount;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    Bank bank = Bank.getInstance();

    BankAccount alice = new BankAccount(101, 1000);
    bank.addAccount(alice);

    Thread reader1 = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      System.out.println(Thread.currentThread().getName()
                         + " Reading Balance : " + account.getBalance());

    }, "Reader-1");


    Thread reader2 = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      System.out.println(Thread.currentThread().getName()
                         + " Reading Balance : " + account.getBalance());

    }, "Reader-2");


    Thread reader3 = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      System.out.println(Thread.currentThread().getName()
                         + " Reading Balance : " + account.getBalance());

    }, "Reader-3");


    Thread writer = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      account.credit(500);

    }, "Writer");


    reader1.start();

    Thread.sleep(1000);
    writer.start();
    Thread.sleep(1000);

    reader2.start();
    reader3.start();

        // Readers start first


//    reader1.join();
//    reader2.join();
//    reader3.join();
//    writer.join();

    System.out.println();
    System.out.println("Final Balance : "
                       + bank.getAccount(101).getBalance());
  }
}