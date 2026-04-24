/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.filter;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.util.logging.Logger;

@Provider
/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    // The logger is used to record  the request and response activity
    private static final Logger logger = Logger.getLogger(LoggingFilter.class.getName());

    @Override
    // this runs before the request is processed and logs the method + URL so that it can be returned
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String method = 
                requestContext.getMethod();
        String path = 
                requestContext.getUriInfo().getRequestUri().toString();

        logger.info("REQUEST: " + method + " " + path);
    }

    @Override
    // This runs before the request is processed to log the http method and URL so that it can be returned 
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        int status = responseContext.getStatus();

        logger.info("RESPONSE STATUS: " + status);
    }
}
