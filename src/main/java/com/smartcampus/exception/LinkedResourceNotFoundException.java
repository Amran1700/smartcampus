/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.smartcampus.exception;

/**
 *
 * @author Amran Mohammed
 * Student id :w2066724
 */

// This exception is sent when a referenced resource like a  room or sensor does not exist
public class LinkedResourceNotFoundException extends RuntimeException{
    
    
    // This is a constructor that accepts a custom error message
    public LinkedResourceNotFoundException(String message) {
        super(message);
    }
    
}
