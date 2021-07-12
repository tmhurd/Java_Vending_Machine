/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.FoodItem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Terri
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, FoodItem> allFoods = new HashMap<>();

    public static final String FOOD_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    @Override
    public FoodItem addItem(String name, FoodItem food) throws VendingMachineDaoException {
        FoodItem newFood = allFoods.put(name, food);
        writeFoodFile();
        return newFood;

    }

    @Override
    public List<FoodItem> getAllItems() throws VendingMachineDaoException {
        loadFoodFile();
        return new ArrayList<FoodItem>(allFoods.values());
    }

    @Override
    public FoodItem getItem(String name) throws VendingMachineDaoException {
        loadFoodFile();
        return allFoods.get(name);
    }

    @Override
    public FoodItem removeFood(String name) throws VendingMachineDaoException {
        FoodItem removedFood = allFoods.remove(name);
        writeFoodFile();
        return removedFood;
    }

    private void loadFoodFile() throws VendingMachineDaoException {
        Scanner scanner;

        try {
            scanner = new Scanner(new BufferedReader(new FileReader("inventory.txt")));
        } catch (FileNotFoundException e) {
            throw new VendingMachineDaoException("-_- Could not load inventory data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            FoodItem currentItem = new FoodItem();
            currentItem.setName(currentTokens[0]);
            currentItem.setPrice(currentTokens[1]);
            currentItem.setAmount(currentTokens[2]);

            allFoods.put(currentItem.getName(), currentItem);

        }

        scanner.close();
    }

    private void writeFoodFile() throws VendingMachineDaoException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter("inventory.txt"));
        } catch (IOException e) {
            throw new VendingMachineDaoException("Could not save inventory data.", e);
        }

        List<FoodItem> foodList = this.getAllItems();
        for (FoodItem currentItem : foodList) {
            out.println(currentItem.getName() + DELIMITER + currentItem.getPrice()
                    + DELIMITER + currentItem.getAmount());

            out.flush();
        }
        out.close();

    }

}
