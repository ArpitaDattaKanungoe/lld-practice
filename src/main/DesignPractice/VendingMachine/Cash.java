package main.DesignPractice.VendingMachine;

public enum Cash {
  FIVE(5),
  TEN(10),
  TWENTY(20);

  private final int value;

  Cash(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
