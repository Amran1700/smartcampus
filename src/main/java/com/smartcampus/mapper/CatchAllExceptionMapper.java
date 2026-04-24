/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.mapper;

import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

import java.util.*;

@Provider

/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Override
    
 // This catches any unexpected errors in the system and makes sure the API always returns a proper error message 

    public Response toResponse(Throwable ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        error.put("status", 500);

        // This converts any error into a http 500 response with a simple JSON message
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .build();
    }
}
