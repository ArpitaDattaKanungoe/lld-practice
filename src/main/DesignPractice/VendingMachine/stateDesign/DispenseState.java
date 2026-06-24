package main.DesignPractice.VendingMachine.stateDesign;

import main.DesignPractice.VendingMachine.Cash;
import main.DesignPractice.VendingMachine.CashInventory;
import main.DesignPractice.VendingMachine.Inventory;
import main.DesignPractice.VendingMachine.MachineState;
import main.DesignPractice.VendingMachine.Product;
import main.DesignPractice.VendingMachine.Transaction;
import main.DesignPractice.VendingMachine.VendingMachine;

public class DispenseState implements MachineState {
//  Deduct stock
//  Store coins in machine
//  Return change
//  Clear transaction
//  Go back to Idle

  private static final DispenseState INSTANCE = new DispenseState();

  private DispenseState() {
  }

  public static DispenseState getInstance() {
    return INSTANCE;
  }

  @Override
  public void selectProduct(VendingMachine machine, Product product) {
    throw new IllegalArgumentException("Already dispensing.");
  }

  @Override
  public void insertCoin(VendingMachine machine, Cash coin) {
    throw new IllegalArgumentException("Already dispensing.");
  }

  @Override
  public void dispense(VendingMachine machine) {
    Transaction transaction = machine.getCurrentTransaction();
    Product product = transaction.getSelectedProduct();
    Inventory productInventory = machine.getProductInventory();
    productInventory.deductProduct(product);
    // Store coins in machine
    CashInventory cashInventory = machine.getCashInventory();
    cashInventory.addCoins(transaction.getInsertedCoins());
    //
    double change = transaction.getTotalInsertedMoney() - product.getPrice();
    if(change>0){
      System.out.println(
          "Returning Change : ₹" + change);
    }
    System.out.println(
        "Dispensing : " + product.getName());
    machine.setCurrentTransaction(null);

    machine.setCurrentState(IdleState.getInstance());
  }

  @Override
  public void cancel(VendingMachine machine) {
    throw new IllegalArgumentException("Cannot cancel now.");
  }
}