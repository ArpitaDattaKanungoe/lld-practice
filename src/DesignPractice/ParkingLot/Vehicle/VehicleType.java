package DesignPractice.ParkingLot.Vehicle;

public enum VehicleType {
  car(20),
  bike(15),
  publicTransport(22);

  private final int chargePerHour;

  VehicleType(int chargePerHour) {
    this.chargePerHour = chargePerHour;
  }

  public int getChargePerHour() {
    return chargePerHour;
  }
}
