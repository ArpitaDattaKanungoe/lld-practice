package main.DesignPractice.InventoryManagementSimple;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InventoryService {

  // Key is made of (productId and storeId)
  Map<String, InventoryItem> inventoryMap = new HashMap<>();

  private String key(String storeId, String productId) {
    return storeId + "_" + productId;
  }

  public InventoryItem registerProductInStore(String storeId, String productId, int qty, int reorder) {
    InventoryItem item = new InventoryItem(UUID.randomUUID().toString(),storeId,productId,qty,reorder);
    inventoryMap.put(key(storeId, productId), item);
    return item;
  }

  public void addStock(String storeId, String productId, int qty) {

    InventoryItem item =
        inventoryMap.get(key(storeId, productId));

    item.addStock(qty);
  }

  public void removeStock(String storeId,
      String productId,
      int qty) {

    InventoryItem item =
        inventoryMap.get(key(storeId, productId));

    boolean ok = item.removeStock(qty);

    if (!ok) {
      throw new RuntimeException("Insufficient stock");
    }
  }

  public int getStock(String storeId, String productId) {

    return inventoryMap
        .get(key(storeId, productId))
        .getQuantity();
  }
}
