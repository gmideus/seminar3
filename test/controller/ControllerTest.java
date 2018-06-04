package controller;

import integration.DBHandler;
import integration.NoSuchCustomerException;
import integration.NoSuchItemException;
import model.InsufficientPaymentException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class ControllerTest {
    Controller contr;
    DBHandler dbHandler;

    @Before
    public void setUp() throws Exception {
        this.dbHandler = new DBHandler();
        this.contr = new Controller(dbHandler);
        contr.initiateSale();
    }

    @After
    public void tearDown() throws Exception {
        this.contr = null;
        this.dbHandler = null;
    }



    @Test
    public void registerExistingItem() {
        try {
            boolean result = contr.registerItem(1, 1);
            boolean expResult = true;
            assertEquals("Result does not match the expected result", expResult, result);
        } catch (Exception ex){
            fail("Unexpected exception when registering item");
        }
    }

    @Test
    public void registerNonExistentItem(){
        try{
            boolean result = contr.registerItem(13,1);
            boolean expResult = false;
            assertEquals("Result does not match the expected result", expResult, result);
        } catch (Exception ex){
            fail("Unexpected exception when registering invalid item.");
        }

    }

    @Test
    public void updateSale() {
        try {
            double expResult = 10;
            contr.registerItem(1, 2);
            double result = contr.updateSale().getRunningTotal();
            assertEquals("Running total does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when updating sale information");
        }

    }

    @Test
    public void updateEmptySale(){
        try {
            double expResult = 0;
            double result = contr.updateSale().getRunningTotal();
            assertEquals("Running total does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when updating sale information");
        }
    }

    @Test
    public void completeSale() {
        try {

            double expResult = 12.5;
            contr.registerItem(1, 2);
            double result = contr.completeSale();
            assertEquals("Total price does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when ending sale");
        }
    }
    
    @Test
    public void completeEmptySale(){
        double expResult = 0;
        double result = contr.completeSale();
        assertEquals("Total price does not match expected results.", expResult, result, 0.01);
    }

    @Test
    public void registerDiscount() {
        try{
            double expResult = 11.25;
            contr.registerItem(1, 2);
            contr.completeSale();
            double result = contr.registerDiscount(0);
            assertEquals("Price after discount does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when registering discount.");
        }
    }

    @Test
    public void registerInvalidDiscount(){
        try {
            contr.completeSale();
            contr.registerDiscount(13);
            fail("Failed to throw exception when registering invalid discount.");
        } catch (NoSuchCustomerException ex){

        } catch (Exception ex){
            fail("Unexpected exception when registering invalid customer.");
        }
    }

    @Test
    public void registerPayment() {
        try{
            double expResult = 2.5;
            contr.registerItem(1,2);
            contr.completeSale();
            double result = contr.registerPayment(15);
            assertEquals("Change does not maych expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when registering payment");
        }
    }

    @Test
    public void registerInsufficientPayment(){
        try{
            contr.registerItem(1, 2);
            contr.completeSale();
            contr.registerPayment(5);
        } catch (InsufficientPaymentException ex){

        } catch (Exception ex){
            fail("Unexpected exception when registering insufficient payment");
        }
    }
}