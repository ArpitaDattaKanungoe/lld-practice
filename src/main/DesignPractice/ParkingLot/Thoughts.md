Main flow:

Vehicle enters from entry gate
|
ParkingLotService validates entry gate
|
ParkingStrategy finds suitable spot
|
ParkingSpot assigns vehicle
|
TicketService generates ticket
|
Vehicle exits from exit gate
|
ParkingLotService validates exit gate
|
TicketService fetches ticket
|
PaymentService calculates amount
|
PaymentStrategy processes payment
|
ParkingSpot is freed
|
Ticket is removed

[//]: # (Key Design Choices)

- ParkingLot owns floors, entry gates, and exit gates.
- ParkingLotService coordinates parking and exit operations.
- NearestParkingStrategy finds the nearest available spot by checking lower floorNumber first.
- ParkingSpot now protects itself from invalid assignment using canFit(vehicle).
- Entry and exit gates are validated using gate IDs like "E1" and "X2".
- Invalid gates throw errors,