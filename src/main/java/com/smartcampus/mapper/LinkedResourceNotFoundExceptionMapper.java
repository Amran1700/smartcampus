/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.mapper;

import com.smartcampus.exception.LinkedResourceNotFoundException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

import java.util.*;

@Provider
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class LinkedResourceNotFoundExceptionMapper implements ExceptionMapper<LinkedResourceNotFoundException> {
    @Override
    public Response toResponse(LinkedResourceNotFoundException ex) {

        // This converts the exception into a 422 response with a simple error message
        Map<String, Object> error = new HashMap<>();
        error.put("error", ex.getMessage());
        error.put("status", 422);

        return Response.status(422)
                .entity(error)
                .build();
    }
    
}
