package main.DesignPractice.VendingMachine.stateDesign;

import main.DesignPractice.VendingMachine.Cash;
import main.DesignPractice.VendingMachine.MachineState;
import main.DesignPractice.VendingMachine.Product;
import main.DesignPractice.VendingMachine.Transaction;
import main.DesignPractice.VendingMachine.VendingMachine;

public class SelectionState implements MachineState {
//  Accept coins
//  Keep updating the current transaction
//  Once enough money is inserted, move to DispenseState
//  cancel this process

  private static final SelectionState INSTANCE = new SelectionState();

  private SelectionState() {
  }

  public static SelectionState getInstance() {
    return INSTANCE;
  }

  @Override
  public void selectProduct(VendingMachine machine, Product product) {
    throw new IllegalArgumentException("Product already selected.");
  }

  @Override
  public void insertCoin(VendingMachine machine, Cash coin) {
    Transaction transaction = machine.getCurrentTransaction();
    transaction.addCoin(coin); // accept coin
    Product product = transaction.getSelectedProduct();
    if (transaction.getTotalInsertedMoney() >= product.getPrice()) {
      machine.setCurrentState(DispenseState.getInstance());
    }
  }

  @Override
  public void dispense(VendingMachine machine) {
    throw new IllegalArgumentException("Please insert sufficient amount.");
  }

  @Override
  public void cancel(VendingMachine machine) {
    Transaction transaction = machine.getCurrentTransaction();
    System.out.println("Returning Coins : " + transaction.getInsertedCoins());
    machine.setCurrentTransaction(null);
    machine.setCurrentState(IdleState.getInstance());
  }
}