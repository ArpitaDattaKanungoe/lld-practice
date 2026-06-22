package DesignPractice.VendingMachine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CashInventory { // Permanent coins owned by the vending machine.

  private final Map<Cash, Integer> cashInventory = new ConcurrentHashMap<>();

  private void addCoin(Cash coin) {

    cashInventory.put(
        coin,
        cashInventory.getOrDefault(coin, 0) + 1
    );
  }

  public void addCoins(List<Cash> coins) {
    for (Cash coin: coins) {
      addCoin(coin);
    }
  }

  public boolean hasCoin(Cash coin) {
    return cashInventory.getOrDefault(coin, 0) > 0;
  }

  public void removeCoin(Cash coin) {

    if (!hasCoin(coin)) {
      throw new IllegalArgumentException("Cash not available.");
    }

    cashInventory.put(
        coin,
        cashInventory.get(coin) - 1
    );
  }

  public void display() {

    System.out.println("\n===== Cash Inventory =====");

    cashInventory.forEach((coin, quantity) ->
        System.out.println(coin + " : " + quantity));
  }
}
