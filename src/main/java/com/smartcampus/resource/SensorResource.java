/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;
import com.smartcampus.model.Sensor;
import com.smartcampus.model.Room;
import com.smartcampus.storage.DataStore;
import com.smartcampus.exception.LinkedResourceNotFoundException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.*;
import java.util.stream.Collectors;

@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class SensorResource {
    
    @GET
    // Gets all sensors
    //they can also be filtered by type
    public Response getSensors(@QueryParam("type") String type) 
    {

        List<Sensor> sensors = new ArrayList<>(DataStore.sensors.values());
        if (type != null) {
            sensors = sensors.stream()
                    .filter(s -> type.equalsIgnoreCase(s.getType()))
                    .collect(Collectors.toList());
        }
        return Response.ok(sensors).build();
    }

    @POST
    // Creates a new sensor and makes sure it is link it to a room
    public Response createSensor(Sensor sensor) 
    {
        // this actually checks if the room exists before adding a sensor
        Room room = DataStore.rooms.get(sensor.getRoomId());
        if (room == null) {
            throw new LinkedResourceNotFoundException("Room does not exist");
        }

        DataStore.sensors.put(sensor.getId(), sensor);
        // Link sensor to the room
        room.getSensorIds().add(sensor.getId());
        return Response.status(201).entity(sensor).build();
    }

    @Path("/{id}/readings")
    public SensorReadingResource getReadings(@PathParam("id") String id)
    {
        return new SensorReadingResource();
    }
}
