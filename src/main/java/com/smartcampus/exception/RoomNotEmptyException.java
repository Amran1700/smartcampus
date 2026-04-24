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

// This exception is sent when you try to to delete a room that still has sensors assigned to it 
public class RoomNotEmptyException extends RuntimeException {
    
     // This is a constructor that accepts a custom error message
     public RoomNotEmptyException(String message) {
        super(message);
    }
}
