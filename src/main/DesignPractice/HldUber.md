Functional Requirements :
Riders should be able to input a start location and a destination and get a fare estimate.
Riders should be able to request a ride based on the estimated fare.
Upon request, riders should be matched with a driver who is nearby and available.
Drivers should be able to accept/decline a request and navigate to pickup/drop-off.

Non-Functional Requirements :
The system should prioritize low latency matching (< 1 minutes to match or failure)
The system should ensure strong consistency in ride matching to prevent any driver from being assigned multiple rides simultaneously
The system should be able to handle high throughput, especially during peak hours or special events (100k requests from same location)

<img width="1024" height="1536" alt="uber" src="https://github.com/user-attachments/assets/bf93e4a6-9282-4a6e-81ed-944517eacb6f" />
