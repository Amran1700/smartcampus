/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.SensorReading;
import com.smartcampus.storage.DataStore;
import com.smartcampus.exception.SensorUnavailableException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/sensors/{sensorId}/readings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class SensorReadingResource {
    
    @PathParam("sensorId")
    private String sensorId;

    // Returns all readings for the sensor
    @GET
    public Response getReadings() 
    {
        List<SensorReading> list =
                DataStore.readings
                        .getOrDefault(sensorId, new ArrayList<>());
        
        return Response.ok(list).build();
    }

    @POST
    // Adds a new sensor reading
    public Response addReading(SensorReading reading) 
    {
    // Check if the sensor exists
        Sensor sensor = DataStore.sensors.get(sensorId);

        if (sensor == null) 
        {
            throw new NotFoundException("The sensor not found");
        }
        // stops the system from adding readings if sensor is under maintenance
        if ("MAINTENANCE".equals(sensor.getStatus())) 
        {
            throw new SensorUnavailableException("Sensor is in maintenance");
        }

        DataStore.readings
                .computeIfAbsent(sensorId, x -> new ArrayList<>()).add(reading);

        sensor.setCurrentValue(reading.getValue());
        return Response.status(201).entity(reading).build();
    }
    
}
