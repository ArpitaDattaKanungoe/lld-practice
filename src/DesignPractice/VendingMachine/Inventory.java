package DesignPractice.VendingMachine;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Inventory {

  private Map<Product, Integer> productStockMap = new ConcurrentHashMap<>();

  public void addProduct(Product product, int quantity) {
    productStockMap.put(product, productStockMap.getOrDefault(product, 0) + quantity);
  }

  public void deductProduct(Product product) {
    if (!isProductAvailable(product)) {
      throw new IllegalArgumentException("Item is out of stock: " + product.getName());
    }
    productStockMap.put(product, productStockMap.get(product) - 1);
  }

  public boolean isProductAvailable(Product product) {
    return productStockMap.getOrDefault(product, 0) > 0;
  }

  public void display() {
    System.out.println("\n=== Available Items ===");
    productStockMap.forEach((item, qty) ->
        System.out.println("  " + item + "  |  Qty: " + qty));
    System.out.println("=======================");
  }
}
