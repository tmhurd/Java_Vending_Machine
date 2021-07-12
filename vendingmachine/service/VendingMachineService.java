/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dto.FoodItem;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Terri
 */
public class VendingMachineService implements VendingMachineServiceInter {

    public VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    public VendingMachineService(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public void addFoodItem(FoodItem newItem) throws VendingMachineDaoException {

        dao.addItem(newItem.getName(), newItem);
        
       // auditDao.writeAuditEntry("Food " + newItem.getName() + " ADDED BY TECHNICIAN");
    }

    @Override
    public String vendFood(String name, String payment) throws VendingMachineDaoException, SelectionNotRecognizedException, ItemOutOfStockException, InsufficientFundsException {

        verifySelection(name);

        FoodItem food = dao.getItem(name);

        if(food.getAmount().equals("0")){
        verifyInventory(food.getAmount());
        } 

        String price = food.getPrice();

        String paymentVerified = paymentValidation(payment, price);

        String change = makeChange(paymentVerified, name, price);

        food.setAmount(deductOneFromAmount(food.getAmount()));

        dao.addItem(name, food);
        
      //  auditDao.writeAuditEntry("Food " + name + " SOLD TO CUSTOMER");

        return change;

    }

    @Override
    public List<FoodItem> getAllItems() throws VendingMachineDaoException {
        return dao.getAllItems();
    }

    @Override
    public FoodItem getItem(String name) throws VendingMachineDaoException {
        return dao.getItem(name);
    }

    @Override
    public FoodItem removeFood(String name) throws SelectionNotRecognizedException, VendingMachineDaoException {
        FoodItem removedItem = dao.removeFood(name);
    //    auditDao.writeAuditEntry("Food " + name + " REMOVED BY TECHNICIAN");
        return removedItem;
    }

    @Override
    public BigDecimal convertToBD(String s) {
        BigDecimal bd = new BigDecimal(s);
        return bd;
    }

    @Override
    public String makeChange(String s, String st, String price) throws InsufficientFundsException {
        ChangeClass changeClass = new ChangeClass();
        BigDecimal bd = convertToBD(s);
        String message = null;
        String changeToReturn = null;

        BigDecimal foodPrice = new BigDecimal(price);
        BigDecimal change = bd.subtract(foodPrice);
        changeToReturn = changeClass.yourChange(change);

        return changeToReturn;

    }

    @Override
    public String deductOneFromAmount(String s) throws ItemOutOfStockException {
        FoodItem food = new FoodItem();
        int amount1 = Integer.parseInt(s) - 1; //add to service layer if works
        String amountS = Integer.toString(amount1);
        food.setAmount(amountS);
        String deduction = food.getAmount();
        return deduction;
    }

    @Override
    public String paymentValidation(String payment, String price) throws InsufficientFundsException, VendingMachineDaoException {
        BigDecimal paymentBD = new BigDecimal(payment);
        BigDecimal priceBD = new BigDecimal(price);

        if (paymentBD.compareTo(priceBD) == -1) {
            
         /*    try {
                auditDao.writeAuditEntry("Insufficient Payment.  See next line........");
            } catch (VendingMachineDaoException ex) {
                 throw new VendingMachineDaoException("Could not persist audit information", ex);
            } */

            throw new InsufficientFundsException("***Insufficient Funds; Your payment of $" + payment + " has been returned.  Please insert correct amount***");

        } else {

            return payment;
        }
    }

    public void verifyInventory(String s) throws ItemOutOfStockException, VendingMachineDaoException {

        int sint = Integer.parseInt(s);
        
        if (sint > 0) {
            // do nothing
        } else {
            /* try {
                auditDao.writeAuditEntry("Item Out Of Stock.  See next line........");
            } catch (VendingMachineDaoException ex) {
                 throw new VendingMachineDaoException("Could not persist audit information", ex);
            } */
            throw new ItemOutOfStockException("***This Item is Out of Stock; Your payment of has been returned. Please make another selection.***");
            
        }
    }

    public void verifySelection(String s) throws SelectionNotRecognizedException {
        if (s.equalsIgnoreCase(null) || s.equals("Needs Verification")) {
            throw new SelectionNotRecognizedException("****Selection Invalid; Your payment has been returned.  Please try again.****");
        }
    }

    public void verifySelection2(String s) throws SelectionNotRecognizedException {
        boolean nomNom = s.equalsIgnoreCase("NomNom");
        boolean add = s.equalsIgnoreCase("Add");
        boolean delete = s.equalsIgnoreCase("Delete");
        boolean exit = s.equalsIgnoreCase("Exit");

        if (nomNom == false && add == false && exit == false && delete == false) {
            throw new SelectionNotRecognizedException("****Selection Invalid; Please try again.****");
        }
    }
}
