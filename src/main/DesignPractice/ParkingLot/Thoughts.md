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

<img width="1484" height="830" alt="Screenshot 2026-07-05 at 11 37 31 AM" src="https://github.com/user-attachments/assets/ed3b9eca-4116-4b86-b2a4-dc43809aa221" />
