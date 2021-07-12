/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.FoodItem;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.ItemOutOfStockException;
import com.sg.vendingmachine.service.SelectionNotRecognizedException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceInter;
import com.sg.vendingmachine.ui.EmptyVendingMachineException;
import com.sg.vendingmachine.ui.View;
import java.util.List;

/**
 *
 * @author Terri
 */
public class Controller {

    private View view;
    private VendingMachineServiceInter service;

    public void run() throws VendingMachineDaoException, InsufficientFundsException, ItemOutOfStockException, SelectionNotRecognizedException {
        boolean keepGoing = true;
        try {

            listFoodItems();

        } catch (EmptyVendingMachineException e) {
            view.printStringToConsole("******THE VENDING MACHINE IS EMPTY.... SORRY!*******");
        }

        try {

            while (keepGoing) {

                String menuSelection = getMenuSelection();

                service.verifySelection2(menuSelection);

                if (menuSelection.equalsIgnoreCase("NomNom")) {
                    vendFood();

                } else if (menuSelection.equalsIgnoreCase("Add")) {
                    addFoodItem();

                } else if (menuSelection.equalsIgnoreCase("Delete")) {
                    deleteFoodItem();

                } else if (menuSelection.equalsIgnoreCase("Exit")) {
                    view.printStringToConsole("^^^***^^^  THANK YOU!! :) ^^^***^^^");
                    break;
                } else {
                    break;
                }

                try {
                    listFoodItems();
                } catch (EmptyVendingMachineException e) {
                    view.printStringToConsole("******THE VENDING MACHINE IS EMPTY.... SORRY!*******");
                }

            }
        } catch (VendingMachineDaoException e) {
            view.displayErrorMessage(e.getMessage());

        }
    }

    private String getMenuSelection() throws VendingMachineDaoException {
        return view.printMenuAndGetSelection();
    }

    private void addFoodItem() throws VendingMachineDaoException {
        FoodItem newItem = view.restockFoodItem();
        service.addFoodItem(newItem);
        view.displayCreateSuccessBanner();
    }

    private void deleteFoodItem() throws VendingMachineDaoException, SelectionNotRecognizedException {
        String foodName = view.getFoodChoice2();
        service.removeFood(foodName);
    }

    private void listFoodItems() throws VendingMachineDaoException, EmptyVendingMachineException {
        List<FoodItem> foodList = service.getAllItems();
        view.displayFoodList(foodList);
    }

    private void vendFood() throws VendingMachineDaoException, InsufficientFundsException, ItemOutOfStockException, SelectionNotRecognizedException {

        boolean hasErrors = false;

        do {

            String name = view.getFoodChoice();
            String payment = view.getPayment();

            try {
                view.printStringToConsole(service.vendFood(name, payment));
                view.displayCreateSuccessBanner2();
                hasErrors = false;
            } catch (VendingMachineDaoException | InsufficientFundsException | ItemOutOfStockException | SelectionNotRecognizedException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);

    }

    public Controller(VendingMachineServiceInter service, View view) {
        this.service = service;
        this.view = view;
    }
}
