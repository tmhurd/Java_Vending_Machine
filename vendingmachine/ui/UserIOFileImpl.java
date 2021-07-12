/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import java.util.Scanner;

/**
 *
 * @author Terri
 */
public class UserIOFileImpl implements UserIO {

    Scanner userInput = new Scanner(System.in);

    private int yourInt;
    private String yourString;

    public UserIOFileImpl() {
    
    }
    
    public String getYourString() {
        return yourString;
    }

    public void setYourString(String yourString) {
        this.yourString = yourString;
    }

    public int getYourInt() {
        return yourInt;
    }

    public void setYourInt(int yourInt) {
        this.yourInt = yourInt;
    }

    public Scanner getUserInput() {
        return userInput;
    }

    public void setUserInput(Scanner userInput) {
        this.userInput = userInput;
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public int readInt(String prompt) {
        int yourInt = 0;
        System.out.println(prompt);
        String yourString = userInput.nextLine();
        yourInt = Integer.valueOf(yourString);
        return yourInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        System.out.println(prompt);

        int yourInt = 0;

        String yourString = userInput.nextLine();

        yourInt = Integer.valueOf(yourString);

        while (yourInt < min || yourInt > max) {
            System.out.println(prompt);
            yourString = userInput.nextLine();
            yourInt = Integer.valueOf(yourString);
        }

        if (yourInt >= min || yourInt <= max) {

        }
        return (yourInt);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        yourString = userInput.nextLine();
        return (yourString);
    }

    @Override
    public String readString2(String prompt) {
        System.out.println(prompt);
        yourString = userInput.nextLine();
        return (yourString);
    }
}
