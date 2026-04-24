/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.mapper;

import com.smartcampus.exception.SensorUnavailableException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

import java.util.*;

@Provider
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class SensorUnavaliableExceptionMapper implements ExceptionMapper<SensorUnavailableException> {
    
    @Override
    public Response toResponse(SensorUnavailableException ex) {

        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", 403);

        // Returns a 403 Forbidden response.
        return Response.status(Response.Status.FORBIDDEN)
                .entity(error)
                .build();
    }
    
}
