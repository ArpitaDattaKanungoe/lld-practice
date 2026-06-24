package main.DesignPractice.InventoryManagementSimple;

public class Product {

  private String productId;
  private String name;
  private double Price;
  private String category;

  public void updateName(String name) {
    this.name = name;
  }

  public void updatePrice(double price) {
    Price = price;
  }

  public void updateCategory(String category) {
    this.category = category;
  }

  public String getName() {
    return name;
  }
}
