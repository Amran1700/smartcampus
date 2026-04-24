/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.mapper;

import com.smartcampus.exception.RoomNotEmptyException;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.*;

import java.util.*;
@Provider
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    @Override
    public Response toResponse(RoomNotEmptyException ex) {

        Map<String, Object> error = new HashMap<>();
        // This the  actual error message from the exception
        error.put("error", ex.getMessage());
        error.put("status", 409);

       // This returns a 409 Conflict response.
        return Response.status(Response.Status.CONFLICT)
                .entity(error)
                .build();
    }
}
