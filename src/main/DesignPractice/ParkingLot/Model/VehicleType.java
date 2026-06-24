package main.DesignPractice.ParkingLot.Model;

public enum VehicleType {
  BIKE(10),
  CAR(20),
  TRUCK(40);

  private final double hourlyRate;

  VehicleType(double hourlyRate) {
    this.hourlyRate = hourlyRate;
  }

  public double getHourlyRate() {
    return hourlyRate;
  }
}
