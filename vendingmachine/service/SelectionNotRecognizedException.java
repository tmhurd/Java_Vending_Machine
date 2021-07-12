/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author Terri
 */
public class SelectionNotRecognizedException extends Exception {
    
    public SelectionNotRecognizedException(String message) {
        super(message);
    }
    
    public SelectionNotRecognizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
