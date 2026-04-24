/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.service;

import com.smartcampus.model.Sensor;
import com.smartcampus.storage.DataStore;
import com.smartcampus.exception.LinkedResourceNotFoundException;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class SensorService {
    // gets all sensors in the system
    public List<Sensor> getAllSensors() 
    {
        return new ArrayList<>(DataStore.sensors.values());
    }

    // Returns sensors filtered bythe type
    public List<Sensor> getByType(String type) 
    {
        return DataStore.sensors.values()
                .stream()
                .filter(x -> x.getType().equalsIgnoreCase(type))
                .collect(Collectors.toList());
    }
 // Creates a sensor and links it to a room
    public void createSensor(Sensor sensor) 
    {

        if (!DataStore.rooms.containsKey(sensor.getRoomId())) 
        {
            throw new LinkedResourceNotFoundException("Room does not exist");
        }

        DataStore.sensors.put(sensor.getId(), sensor);
        DataStore.rooms.get(sensor.getRoomId())
                .getSensorIds()
                .add(sensor.getId());
    }
}
