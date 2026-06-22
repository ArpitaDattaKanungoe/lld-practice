package DesignPractice.VendingMachine;

public interface MachineState {

  void selectProduct(VendingMachine machine, Product product);

  void insertCoin(VendingMachine machine, Cash coin);

  void dispense(VendingMachine machine);

  void cancel(VendingMachine machine);
}
