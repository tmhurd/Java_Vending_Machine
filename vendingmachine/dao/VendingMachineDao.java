/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.FoodItem;
import java.util.List;

/**
 *
 * @author Terri
 */
public interface VendingMachineDao {
    
    FoodItem addItem(String name, FoodItem food) throws 
            VendingMachineDaoException;
    
    List<FoodItem> getAllItems() throws 
            VendingMachineDaoException;
    
     public FoodItem getItem(String name) throws 
             VendingMachineDaoException;
     
     public FoodItem removeFood(String name) throws 
             VendingMachineDaoException;
}
