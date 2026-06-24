package main.DesignPractice.VendingMachine;

public class Product {

  double price;
  String name;

  public Product(double price, String name) {
    this.price = price;
    this.name = name;
  }

  public double getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Product)) {
      return false;
    }

    Product other = (Product) obj;

    return name.equalsIgnoreCase(other.name);
  }

  @Override
  public int hashCode() {
    return name.toLowerCase().hashCode();
  }

  @Override
  public String toString() {
    return name + " (₹" + price + ")";
  }
}
