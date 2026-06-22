package DesignPractice.VendingMachine;

import DesignPractice.VendingMachine.stateDesign.IdleState;

public class VendingMachine {

  Cash coin;
  MachineState currentState;
  Inventory productInventory;
  CashInventory cashInventory;
  Transaction currentTransaction;

  public VendingMachine() {

    this.productInventory = new Inventory();   // <-- IMPORTANT

    this.cashInventory = new CashInventory();

    this.currentState = IdleState.getInstance();
  }
  public void selectProduct(Product product) {
    currentState.selectProduct(this, product);
  }

  void insertCoin(Cash coin) {
    currentState.insertCoin(this, coin);
  }

  void dispense() {
    currentState.dispense(this);
  }

  void cancel() {
    currentState.cancel(this);
  }

  public Cash getCoin() {
    return coin;
  }

  public MachineState getCurrentState() {
    return currentState;
  }

  public Inventory getProductInventory() {
    return productInventory;
  }

  public void setCurrentState(MachineState currentState) {
    this.currentState = currentState;
  }

  public Transaction getCurrentTransaction() {
    return currentTransaction;
  }

  public void setCurrentTransaction(Transaction currentTransaction) {
    this.currentTransaction = currentTransaction;
  }

  public CashInventory getCashInventory() {
    return cashInventory;
  }

  public void setCashInventory(CashInventory cashInventory) {
    this.cashInventory = cashInventory;
  }
}
