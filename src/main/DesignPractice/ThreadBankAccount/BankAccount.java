package main.DesignPractice.ThreadBankAccount;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

  private final long accountNumber;
  private double balance;

  private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

  private static final int MAX_TRANSACTIONS = 5;

  private final Deque<Transaction> transactions = new ArrayDeque<>();

  public BankAccount(long accountNumber, double initialBalance) {
    this.accountNumber = accountNumber;
    this.balance = initialBalance;
  }

  public void credit(double amount) {

    rwLock.writeLock().lock();

    try {

      balance += amount;

      addTransaction(new Transaction("CREDIT", amount));

      System.out.println(Thread.currentThread().getName()
                         + " credited " + amount
                         + " to Account " + accountNumber
                         + " Balance = " + balance);

    } finally {
      rwLock.writeLock().unlock();
    }
  }

  public void debit(double amount) {

    rwLock.writeLock().lock();

    try {

      if (balance < amount) {
        System.out.println("Insufficient Balance");
        return;
      }

      balance -= amount;

      addTransaction(new Transaction("DEBIT", amount));

      System.out.println(Thread.currentThread().getName()
                         + " debited " + amount
                         + " from Account " + accountNumber
                         + " Balance = " + balance);

    } finally {
      rwLock.writeLock().unlock();
    }
  }

  public double getBalance() {

    rwLock.readLock().lock();

    try {
      return balance;
    } finally {
      rwLock.readLock().unlock();
    }
  }

  public List<Transaction> getLastTransactions() {
    rwLock.readLock().lock();
    try {
      return new ArrayList<>(transactions);
    } finally {
      rwLock.readLock().unlock();
    }
  }

  private void addTransaction(Transaction transaction) {

    if (transactions.size() == MAX_TRANSACTIONS) {
      transactions.removeFirst();
    }

    transactions.addLast(transaction);
  }

  public long getAccountNumber() {
    return accountNumber;
  }
}