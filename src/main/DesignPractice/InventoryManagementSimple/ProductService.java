package main.DesignPractice.InventoryManagementSimple;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductService {

  Map<String, Product> productMap = new ConcurrentHashMap<>();

  void addProduct(Product product) {
    productMap.computeIfAbsent(product.getName(), (k) -> product);
  }

  void updateProduct(String name, double price, String category) {
    if (productMap.containsKey(name)) {
      Product product = productMap.get(name);
      product.updateName(name);
      product.updatePrice(price);
      product.updateCategory(category);
    }
  }

  Product getProduct(String name) {
    return productMap.get(name);
  }

}
