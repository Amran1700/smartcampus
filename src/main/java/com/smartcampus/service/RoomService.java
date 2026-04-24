/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.service;
import com.smartcampus.model.Room;
import com.smartcampus.storage.DataStore;
import com.smartcampus.exception.RoomNotEmptyException;

import java.util.*;

/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class RoomService {
    
    // This gets all rooms in the system
    public List<Room> getAllRooms() 
    {
        return new ArrayList<>(DataStore.rooms.values());
    }

    //This gets a single room by the ID
    public Room getRoom(String id) 
    {
        return DataStore.rooms.get(id);
    }

    // adds a new room 
    public void createRoom(Room room)
    {
        DataStore.rooms.put(room.getId(), room);
    }

    // Deletes a room if it exists and has no sensors
    public void deleteRoom(String id)
    {

        Room room = DataStore.rooms.get(id);
        if (room == null)
        {
            return;
        }
        // stops the room from being deleted if sensors are still linked
        if (!room.getSensorIds().isEmpty()) 
        {
            throw new RoomNotEmptyException("Room has active sensors");
        }

        DataStore.rooms.remove(id);
    }
    
}
