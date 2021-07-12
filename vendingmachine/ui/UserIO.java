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
public interface UserIO {

    void print(String message);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max);

    String readString(String prompt);

    String readString2(String prompt);

}
