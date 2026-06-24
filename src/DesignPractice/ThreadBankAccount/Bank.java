package DesignPractice.ThreadBankAccount;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Bank {

  private static final Bank INSTANCE = new Bank();

  private final Map<Long, BankAccount> accounts =
      new ConcurrentHashMap<>();

  private Bank() {
  }

  public static Bank getInstance() {
    return INSTANCE;
  }

  public void addAccount(BankAccount account) {
    accounts.put(account.getAccountNumber(), account);
  }

  public BankAccount getAccount(long accountNumber) {
    return accounts.get(accountNumber);
  }
}
