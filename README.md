# Smart Campus API  
Amran Mohammed — w2066724

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
curl -X POST http://localhost:8080/smartcampus/api/v1/rooms -H "Content-Type: application/json" -d '{ "id": "2001", "name": "meeting hall", "capacity": 550 }'

Get All Rooms  
curl -X GET http://localhost:8080/smartcampus/api/v1/rooms

Get Room by ID  
curl -X GET http://localhost:8080/smartcampus/api/v1/rooms/2001

Delete Room  
curl -X DELETE http://localhost:8080/smartcampus/api/v1/rooms/2001

Create Sensor  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/ -H "Content-Type: application/json" -d '{ "id": "12344", "type": "CO2", "status": "active", "currentValue": 30.889, "roomId": "2001" }'

Get All Sensors  
curl -X GET http://localhost:8080/smartcampus/api/v1/sensors/

Filter Sensors  
curl -X GET "http://localhost:8080/smartcampus/api/v1/sensors?type=CO2"

Add Sensor Reading  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/12344/readings/ -H "Content-Type: application/json" -d '{ "id": "5300", "timestamp": 4253749273, "value": 44.33 }'

Delete Room with Sensors (Conflict Case)  
curl -X DELETE http://localhost:8080/smartcampus/api/v1/rooms/10

Invalid Sensor (422 Case)  
curl -X POST http://localhost:8080/smartcampus/api/v1/sensors/ -H "Content-Type: application/json" -d '{ "id": "222", "type": "CO2", "status": "active", "currentValue": 70.789, "roomId": "5000" }'
