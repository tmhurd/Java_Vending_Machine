/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.FoodItem;
import com.sg.vendingmachine.service.SelectionNotRecognizedException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Terri
 */
public class View {

    UserIO io;
    Scanner userInput = new Scanner(System.in);

    public String printMenuAndGetSelection() {
        io.print("Type 'NomNom' to Purchase Food.");
        io.print("Type 'Exit' to Exit Vending Maching");
        io.print("Enter Service Password to Stock  ***FOR VENDING MACHINE TECHNICIAN ONLY***");

        return io.readString("Please type your selection and press 'Enter'");
    }

    public List displayFoodList(List<FoodItem> foodList) throws EmptyVendingMachineException {
        for (FoodItem currentItem : foodList) {
            io.print(currentItem.getName() + " --- $" + currentItem.getPrice() + " --- " + currentItem.getAmount() + " available");
        }

        if (foodList.size() == 0) {
            throw new EmptyVendingMachineException("******THE VENDING MACHINE IS EMPTY.... SORRY!*******");
        } else {
            return foodList;
        }

    }

    public FoodItem restockFoodItem() {
        String name = io.readString("Please enter the food name.");
        String price = io.readString("Please enter the price for this item.");
        String amount = io.readString("How many of this item are you adding?");

        FoodItem currentItem = new FoodItem();
        currentItem.setName(name);
        currentItem.setPrice(price);
        currentItem.setAmount(amount);

        return currentItem;
    }

    public void displayCreateSuccessBanner() {
        io.print("");
        io.readString2("Food successfully stocked. Please press enter.");
        io.readString2("What would you like to do next? Press enter.");
    }

    public void displayCreateSuccessBanner2() {
        io.print("");
        io.readString2("Thank you for your purchase. Please press enter.");
        io.readString2("What would you like to do next? Press enter.");
    }

    public String getPayment() {
        String payment = io.readString("Please insert payment for this item.");
        return payment;
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public String getFoodChoice() {
        String choice = io.readString("What food would you like to purchase?");
        String choice2 = null;

        if (choice.equalsIgnoreCase("Fries")) {
            choice2 = "Fries";
        } else if (choice.equalsIgnoreCase("Ice Cream")) {
            choice2 = "Ice Cream";
        } else if (choice.equalsIgnoreCase("Chocolate")) {
            choice2 = "Chocolate";
        } else if (choice.equalsIgnoreCase("Caramel")) {
            choice2 = "Caramel";
        } else if (choice.equalsIgnoreCase("Pizza Pockets")) {
            choice2 = "Pizza Pockets";
        } else if (choice.equalsIgnoreCase("Pretzels")) {
            choice2 = "Pretzels";
        } else {
            choice2 = "Needs Verification";
        }
        return choice2;
    }

    public String getFoodChoice2() throws SelectionNotRecognizedException {
        String choice = io.readString("What food would you like to remove?");
        String choice2 = null;

        if (choice != null) {

            if (choice.equalsIgnoreCase("Fries")) {
                choice2 = "Fries";
            } else if (choice.equalsIgnoreCase("Ice Cream")) {
                choice2 = "Ice Cream";
            } else if (choice.equalsIgnoreCase("Chocolate")) {
                choice2 = "Chocolate";
            } else if (choice.equalsIgnoreCase("Caramel")) {
                choice2 = "Caramel";
            } else if (choice.equalsIgnoreCase("Pizza Pockets")) {
                choice2 = "Pizza Pockets";
            } else if (choice.equalsIgnoreCase("Pretzels")) {
                choice2 = "Pretzels";
            } else {
                choice2 = "Needs Verification";
            }
        } else {
            throw new SelectionNotRecognizedException("***********Item unailable, please try again.**********");
        }
        return choice2;
    }

    public void displayFood(FoodItem food) {
        if (food != null) {
            io.print(food.getName());
            io.print(food.getPrice());
            io.print(food.getAmount());
            io.print("");
        } else {
            io.print("No such food.");
        }
        io.readString("Enter");
    }

    public void printStringToConsole(String message) {
        io.print(message);
    }

    public View(UserIO io) {
        this.io = io;
    }
}
