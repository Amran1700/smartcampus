/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import com.smartcampus.model.Room;
import com.smartcampus.storage.DataStore;
import com.smartcampus.exception.RoomNotEmptyException;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import java.util.*;

@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class RoomResource {
    
    
    @GET
    public Response getRooms() 
    {
        return Response.ok(DataStore.rooms.values())
                .build();
    }

    // Creates a new room and stores it in datastore
    @POST
    public Response createRoom(Room room) 
    {
        DataStore.rooms
                .put(room.getId(), room);
        return Response.status(201)
                .entity(room)
                .build();
    }

    
    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") String id) {
        return Response.ok(DataStore
                .rooms.get(id))
                .build();
    }

    // Deletes a room if it exists and it has no sensors
    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") String id) {

        Room room = DataStore.rooms.get(id);

        // If room doesn't exist, return 404
        if (room == null) 
        {
            throw new NotFoundException();
        }

        // stops deletion if sensors are still assigned
        if (!room.getSensorIds().isEmpty()) 
        {
            throw new RoomNotEmptyException("This room has sensors");
        }

        // Removes the room from storage
        DataStore.rooms.remove(id);

        return Response.noContent().build();
    }
    
}
