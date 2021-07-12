/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.Controller;
import com.sg.vendingmachine.dao.VendingMachineDaoException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.ItemOutOfStockException;
import com.sg.vendingmachine.service.SelectionNotRecognizedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 *
 * @author Terri
 */
public class App {
    public static void main(String[] args) throws VendingMachineDaoException, InsufficientFundsException, ItemOutOfStockException, SelectionNotRecognizedException {
        
   // UserIO myIO = new UserIOFileImpl();
   // View myView = new View(myIO);
   // VendingMachineDao myDao = new VendingMachineDaoFileImpl();
   // VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
   // VendingMachineServiceInter myService = new VendingMachineService(myDao, myAuditDao);
   // Controller controller = new Controller(myService, myView);
   // controller.run();
      
   ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
   
   Controller controller = ctx.getBean("controller", Controller.class);
   
   controller.run();
        
    }
}
