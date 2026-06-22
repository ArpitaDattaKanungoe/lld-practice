package DesignPractice.VendingMachine.stateDesign;

import DesignPractice.VendingMachine.Cash;
import DesignPractice.VendingMachine.Inventory;
import DesignPractice.VendingMachine.MachineState;
import DesignPractice.VendingMachine.Product;
import DesignPractice.VendingMachine.Transaction;
import DesignPractice.VendingMachine.VendingMachine;

public class IdleState implements MachineState {
  // will select product in this state

  private static final IdleState INSTANCE = new IdleState();

  private IdleState() {
  }

  public static IdleState getInstance() {
    return INSTANCE;
  }

  @Override
  public void selectProduct(VendingMachine machine, Product product) {
    Inventory inventory = machine.getProductInventory();
    if (!inventory.isProductAvailable(product)) {
      throw new IllegalArgumentException(product.getName() + " is out of stock.");
    }
    Transaction transaction = new Transaction(product); // here it's creating the current transaction with product ( product added )
    machine.setCurrentTransaction(transaction);
    machine.setCurrentState(SelectionState.getInstance());
    System.out.println("Selected Product : " + product.getName());
  }

  @Override
  public void insertCoin(VendingMachine machine, Cash coin) {
    throw new IllegalArgumentException("Please select a product first.");

  }

  @Override
  public void dispense(VendingMachine machine) {
    throw new IllegalArgumentException("Please select a product first.");

  }

  @Override
  public void cancel(VendingMachine machine) {
    throw new IllegalArgumentException("Nothing to cancel");
  }

}
