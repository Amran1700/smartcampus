/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.*;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 * 
 * This is the discovery endpoint.
 * When the API is accessed at /api/v1, it returns basic API info
 */
public class DiscoveryResource {
    @GET
    public Response getApiInfo() {

        Map<String, Object> res = new HashMap<>();

        res.put("version", "v1");
        res.put("status", "Smart Campus API running");

        Map<String, String> links = new HashMap<>();
        links.put("rooms", "/api/v1/rooms");
        links.put("sensors", "/api/v1/sensors");

        res.put("resources", links);

        return Response.ok(res).build();
    }
}
