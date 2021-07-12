/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Terri
 */
public class ChangeClass {

    public String yourChange(BigDecimal bd) {

        BigDecimal quarter = new BigDecimal(".25");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal penny = new BigDecimal(".01");

        BigDecimal firstDivide = bd.divide(quarter, 0, RoundingMode.DOWN);         // quarters
        BigDecimal changeInQuarters = firstDivide.multiply(quarter);
        BigDecimal changeLeft1 = bd.subtract(changeInQuarters);

        BigDecimal secondDivide = changeLeft1.divide(dime, 0, RoundingMode.DOWN); // dimes
        BigDecimal changeInDimes = secondDivide.multiply(dime);
        BigDecimal changeLeft2 = changeLeft1.subtract(changeInDimes);

        BigDecimal thirdDivide = changeLeft2.divide(nickel, 0, RoundingMode.DOWN);  //nickels
        BigDecimal changeInNickels = thirdDivide.multiply(nickel);
        BigDecimal changeLeft3 = changeLeft2.subtract(changeInNickels);

        BigDecimal fourthDivide = changeLeft3.divide(penny, 0, RoundingMode.DOWN); //pennies

        return "Your change is " + firstDivide + " quarters, " + secondDivide + " dimes, " + thirdDivide
                + " nickels, " + fourthDivide + " pennies.";
    }

}
