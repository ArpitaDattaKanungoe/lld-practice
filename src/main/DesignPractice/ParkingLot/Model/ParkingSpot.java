package main.DesignPractice.ParkingLot.Model;

public class ParkingSpot {
  private final String spotId;

  private final VehicleType vehicleType;

  private boolean occupied;

  private Vehicle currentVehicle;


  public ParkingSpot(String spotId, VehicleType vehicleType) {
    this.spotId = spotId;
    this.vehicleType = vehicleType;
    this.currentVehicle = null;
  }

  public void assignVehicle(Vehicle vehicle) {
    if (!canFit(vehicle)) {
      throw new IllegalStateException("Vehicle cannot be assigned to this spot");
    }
    this.currentVehicle = vehicle;
    this.occupied = true;
  }

  public void removeVehicle(){
    this.currentVehicle = null;
    this.occupied = false;
  }

  public boolean canFit(Vehicle vehicle) {
    return !occupied && vehicle.getVehicleType() == vehicleType;
  }

}
