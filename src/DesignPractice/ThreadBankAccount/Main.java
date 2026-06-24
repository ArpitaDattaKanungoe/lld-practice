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


    Thread writer1 = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      account.credit(500);

    }, "Writer");

    Thread writer2 = new Thread(() -> {
      BankAccount account = bank.getAccount(101);

      account.credit(500);

    }, "Writer");


    reader1.start();

    Thread.sleep(1000);
    writer1.start();
    Thread.sleep(1000);
    System.out.println("Last Transaction : "+ bank.getAccount(101).getLastTransactions());

    reader2.start();
    writer2.start();
    reader3.start();


    System.out.println();
    System.out.println("Final Balance : " + bank.getAccount(101).getBalance());
  }
}