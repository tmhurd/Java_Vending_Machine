/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

/**
 *
 * @author Terri
 */
public class EmptyVendingMachineException extends Exception {
    
     public EmptyVendingMachineException(String message) {
        super(message);
    }
    
    public EmptyVendingMachineException(String message, Throwable cause) {
        super(message, cause);
    }
}
