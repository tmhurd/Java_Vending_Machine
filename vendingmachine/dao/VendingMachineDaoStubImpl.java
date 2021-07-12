/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.FoodItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Terri
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private FoodItem food;
    private List<FoodItem> foodList = new ArrayList<>();

    public VendingMachineDaoStubImpl() {
        food = new FoodItem();
        food.setName("Fries");
        food.setPrice("2.22");
        food.setAmount("1");

        foodList.add(food);
    }

    @Override
    public FoodItem addItem(String name, FoodItem food) throws VendingMachineDaoException {
        return food;
    }

    @Override
    public List<FoodItem> getAllItems() throws VendingMachineDaoException {
        return foodList;
    }

    @Override
    public FoodItem getItem(String name) throws VendingMachineDaoException {
        if (name.equals(food.getName())) {
            return food;
        } else {
            return null;
        }
    }

    @Override
    public FoodItem removeFood(String name) throws VendingMachineDaoException {
        if (name.equals(food.getName())) {
            return food;
        } else {
            return null;
        }
    }

}
