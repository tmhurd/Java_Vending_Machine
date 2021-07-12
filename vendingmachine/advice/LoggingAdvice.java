/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.ItemOutOfStockException;
import com.sg.vendingmachine.service.SelectionNotRecognizedException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author Terri
 */
public class LoggingAdvice {

    VendingMachineAuditDao auditDao;

    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
      public void createAuditEntry2(JoinPoint jp, InsufficientFundsException ife) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Insufficient Funds: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
      
      public void createAuditEntry3(JoinPoint jp, ItemOutOfStockException ex) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Item out of Stock: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    } 
      
     public void createAuditEntry4(JoinPoint jp, SelectionNotRecognizedException snr) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Selection not recognized: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachineDaoException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    } 
   

}
