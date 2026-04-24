# Smart Campus 
Author: Amran Mohammed \
StudentID: w2066724

## How to Run

Open the project in NetBeans  
Clean and Build the project  
Run using Tomcat (or embedded server)  
Open the following URL in a browser:  
http://localhost:8080/smartcampus/api/v1/

## CURL COMMANDS

Discovery Endpoint  
curl -X GET http://localhost:8080/smartcampus/api/v1/

Create Room  
curl -X POST http://localhost:8080/smartcampus/api/v1/rooms \
-H "Content-Type: application/json" \
-d '{ "id": "2001", "name": "meeting hall", "capacity": 550 }'

Get All Rooms  
curl -X GET http://localhost:8080/smartcampus/api/v1/rooms

Get Room by ID  
curl -X GET http://localhost:8080/smartcampus/api/v1/rooms/2001

Delete Room  
curl -X DELETE http://localhost:8080/smartcampus/api/v1/rooms/2001

Create Sensor  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/ \
-H "Content-Type: application/json" \
-d '{ "id": "12344", "type": "CO2", "status": "active", "currentValue": 30.889, "roomId": "2001" }'

Get All Sensors  
curl -X GET http://localhost:8080/smartcampus/api/v1/sensors/

Filter Sensors  
curl -X GET "http://localhost:8080/smartcampus/api/v1/sensors?type=CO2"

Add Sensor Reading  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/12344/readings/ \
-H "Content-Type: application/json" \
-d '{ "id": "5300", "timestamp": 4253749273, "value": 44.33 }'

Delete Room with Sensors (Conflict Case)  
curl -X DELETE http://localhost:8080/smartcampus/api/v1/rooms/10

Invalid Sensor (422 Case)  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/ \
-H "Content-Type: application/json" \
-d '{ "id": "222", "type": "CO2", "status": "active", "currentValue": 70.789, "roomId": "5000" }'

## REPORT
1.1
JAX-RS creates a new instance of a resource class for every request. This means objects do not store data between requests. Because of this, I used a shared DataStore class with static HashMaps to store application data. This ensures the data remains available while the server is running. Since multiple requests can happen at the same time, thread safety is important, so synchronisation is needed to prevent race conditions


1.2
HATEOAS means the API returns links to other related endpoints inside the response. For example, the /api/v1 discovery endpoint returns links to resources like rooms and sensors. This helps developers because they do not need to memorise all endpoint URLs or rely heavily on static documentation.


2.1
Returning only IDs makes responses smaller and reduces bandwidth usage, but requires the client to make additional requests to retrieve full details . Returning full objects includes all data in one response, reducing the number of requests and simplifying client-side logic, but increases payload size and may transfer unnecessary data.


2.2
DELETE is idempotent because repeating the same request does not change the final system state. When a room is deleted the first time, it is removed from the system. If the same DELETE request is sent again, the room is already absent, so no further changes occur. In some cases, the request may return an error , but idempotency refers to the fact that the overall system state remains unchanged after repeated requests


3.1
@Consumes(MediaType.APPLICATION_JSON) means the endpoint only accepts JSON input. If a client sends data in another format like text/plain or application/xml, JAX-RS will not be able to process it for that method. The request will be rejected before it reaches the resource method because there is no matching way to convert the incoming data into the expected format. This makes sure   that only valid JSON data is processed by the API.


3.2
Query parameters are better for filtering because they are flexible and optional. For example, /sensors?type=CO2 allows filtering a collection without changing the endpoint structure. Multiple filters can also be combined easily. This approach is more scalable and expressive for searching collections. In contrast, using path parameters (e.g. /sensors/type/CO2) creates a rigid URL structure that is better suited to identifying specific resources rather than applying multiple or evolving filters, and does not scale well as more filter options are added.


4.1
I used a separate class for sensor readings to improve code organisation. The SensorResource class handles sensor logic, while the SensorReadingResource class handles reading data functionality. This separation follows the single responsibility principle, meaning each class is responsible for one part of the system, which makes the code easier to understand and maintain. It also avoids creating one large monolithic resource class that handles many nested routes and responsibilities. Additionally, this improves scalability because new sub-resources can be added without needing to modify existing classes.


5.2
HTTP 422 is more appropriate because the request itself is syntactically valid, but the data inside it is semantically incorrect. In this case, the client sends a sensor with a roomId that does not exist. A 404 is not accurate because the endpoint exists and is correct. The issue is not with the URL but with the relationship between the data being sent and existing resources. Therefore, 422 Unprocessable Entity is more accurate because it indicates the server understands the request but cannot process it due to invalid data.


5.4
Returning stack traces is a security risk because it exposes internal system details such as class names, file paths, frameworks, and dependencies. Attackers can use this information to identify vulnerabilities in the system. To prevent this, a CatchAllExceptionMapper is used to catch errors and return a generic error message instead of exposing internal details.


5.5
Using JAX-RS filters for logging is better than adding logging statements in every method because it centralises logging in one place. This reduces code duplication and ensures consistent logging across all endpoints. It also improves maintainability because logging behaviour can be changed in one location without modifying individual resource classes, supporting better separation of concerns

