package main.DesignPractice.InventoryManagementSimple;

import java.util.concurrent.locks.ReentrantLock;

public class InventoryItem {

  String inventoryItemId;
  String storeId;
  String productId;
  int quantity;
  int reorderLevel;
  private final ReentrantLock lock = new ReentrantLock();

  public InventoryItem(String inventoryItemId, String storeId, String productId, int quantity, int reorderLevel) {
    this.inventoryItemId = inventoryItemId;
    this.storeId = storeId;
    this.productId = productId;
    this.quantity = quantity;
    this.reorderLevel = reorderLevel;
  }

  public boolean isLowStock() {
    return quantity <= reorderLevel;
  }

  public boolean removeStock(int qty) {

    lock.lock();
    try {
      if (quantity < qty) {
        return false;
      }

      quantity -= qty;
      return true;

    } finally {
      lock.unlock();
    }
  }

  public void addStock(int qty) {
    lock.lock();
    try {
      quantity += qty;
    } finally {
      lock.unlock();
    }
  }

  public int getQuantity() {
    return quantity;
  }
}
