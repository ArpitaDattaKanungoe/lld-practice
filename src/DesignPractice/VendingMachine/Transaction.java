package DesignPractice.VendingMachine;

import java.util.ArrayList;
import java.util.List;

public class Transaction {

  private final Product selectedProduct;

  private final List<Cash> insertedCoins;

  private int totalInsertedMoney;

  public Transaction(Product selectedProduct) {

    this.selectedProduct = selectedProduct;
    this.insertedCoins = new ArrayList<>();
    this.totalInsertedMoney = 0;
  }

  public void addCoin(Cash coin) {

    insertedCoins.add(coin);

    totalInsertedMoney += coin.getValue();
  }

  public Product getSelectedProduct() {
    return selectedProduct;
  }

  public List<Cash> getInsertedCoins() {
    return insertedCoins;
  }

  public int getTotalInsertedMoney() {
    return totalInsertedMoney;
  }
}
