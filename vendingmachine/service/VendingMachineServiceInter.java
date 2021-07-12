/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.FoodItem;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Terri
 */
public interface VendingMachineServiceInter {

    public String vendFood(String name, String payment) throws VendingMachineDaoException, SelectionNotRecognizedException, ItemOutOfStockException, InsufficientFundsException;

    public List<FoodItem> getAllItems() throws
            VendingMachineDaoException;

    public FoodItem getItem(String name) throws
            VendingMachineDaoException;

    public void addFoodItem(FoodItem newItem) throws
            VendingMachineDaoException;

    public FoodItem removeFood(String name) throws
            SelectionNotRecognizedException,
            VendingMachineDaoException;

    public BigDecimal convertToBD(String s);

    public String makeChange(String s, String st, String price) throws
            InsufficientFundsException;

    public String deductOneFromAmount(String s) throws
            ItemOutOfStockException;

    public String paymentValidation(String payment, String price) throws
            InsufficientFundsException,
            VendingMachineDaoException;

    public void verifyInventory(String s) throws
            ItemOutOfStockException,
            VendingMachineDaoException;

    public void verifySelection(String s) throws
            SelectionNotRecognizedException;

    public void verifySelection2(String s) throws
            SelectionNotRecognizedException;

}
