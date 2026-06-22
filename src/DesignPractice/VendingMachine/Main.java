package DesignPractice.VendingMachine;

public class Main {

  public static void main(String[] args) {

    // Create Vending Machine
    VendingMachine machine = new VendingMachine();

    // Create Products
    Product coke = new Product(30,"Coke");
    Product pepsi = new Product(40,"Pepsi");
    Product chips = new Product(20,"Chips");

    // Load Inventory
    machine.getProductInventory().addProduct(coke, 5);
    machine.getProductInventory().addProduct(pepsi, 3);
    machine.getProductInventory().addProduct(chips, 2);

    // Display Products
    machine.getProductInventory().display();

    System.out.println("\n============================");
    System.out.println("Customer Buying Coke");
    System.out.println("============================");

    // Step 1 : Select Product
    machine.selectProduct(coke);

    // Step 2 : Insert Coins
    machine.insertCoin(Cash.TEN);
    machine.insertCoin(Cash.TEN);
    machine.insertCoin(Cash.TEN);

    // Step 3 : Dispense Product
    machine.dispense();

    System.out.println();

    // Display Inventory Again
    machine.getProductInventory().display();

    System.out.println();

    // Display Machine Cash
    machine.getCashInventory().display();
  }
}
