package model;

import integration.DBHandler;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import integration.NoSuchItemException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SaleTest {
    private Sale sale;
    private DBHandler dbHandler;

    @Before
    public void setUp(){
        this.dbHandler = new DBHandler();
        this.sale = new Sale(this.dbHandler);
    }

    @After
    public void CleanUp(){
        this.sale = null;
        this.dbHandler = null;
    }

    @Test
    public void addExistingItem() {
        try{
            double expResult = 42.5;
            sale.addItem(17, 2);
            sale.addItem(42, 1);
            assertEquals("runningTotal does not match expected results", expResult, sale.getSaleInfo().getRunningTotal(), 0.01);

        } catch (DBUnavailableException ex){
            fail("Unexpected BDUnavailableException");
        } catch (NoSuchItemException ex){
            fail("Unexpected NoSuchItemException");
        } catch (InvalidArgumentException ex){
            fail("Unexpected InvalidArgumentException");
        }
    }

    @Test
    public void addNegativeItemQuantity() {
        try {
            sale.addItem(17, -13);
            fail("Negative item quantity added");
        } catch (DBUnavailableException ex){
            fail("Unexpected BDUnavailableException");
        } catch (NoSuchItemException ex){
            fail("Unexpected NoSuchItemException");
        } catch (InvalidArgumentException ex){

        }
    }

    @Test
    public void addNonExistentItem(){
        try {
            sale.addItem(-1, 4);
            fail("Added non-existent item");
        } catch (NoSuchItemException ex){

        } catch (Exception ex){
            fail("Unexpected exception when adding non-existent ex");
        }
    }

    @Test
    public void getEmptySaleInfo() {
        double expResult = 0;
        double result = sale.getSaleInfo().getRunningTotal();
        assertEquals("Sale info does not match expected result", expResult, result, 0.01);

    }

    @Test
    public void getSaleInfo() {
        try{
            sale.addItem(1,1);
            double expResult = 5;
            double result = sale.getSaleInfo().getRunningTotal();
            assertEquals("Sale info does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception when trying to get sale info");
        }


    }

    @Test
    public void endSale() {
        try {
            sale.addItem(1, 1);
            sale.addItem(1, 1);
        } catch (Exception ex) {
            fail("Unexpected exception adding items");
        }
        double expResult = 12.5;
        double result = sale.endSale();
        assertEquals("endSale does not return the expected total price", expResult, result, 0.01);
    }

    @Test
    public void endEmptySale(){
        double expResult = 0;
        assertEquals("ending a new sale does not return 0", expResult, sale.endSale(), 0.01);
    }

    @Test
    public void addRegularDiscount() {
        try{
            sale.addItem(1, 2);
            sale.endSale();
            double result = sale.addDiscount(0);
            double expResult = 11.25;
            assertEquals("Discount result does not match expected result", expResult, result, 0.01);
        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void addInvalidDiscount(){
        try{
            sale.addDiscount(-1);
            fail("Failed to throw exception when using invalid customer ID");
        } catch (NoSuchCustomerException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void registerNormalPayment() {
        try{
            sale.addItem(1, 2);
            sale.endSale();
            double result = sale.registerPayment(15);
            double expResult = 2.5;
            assertEquals("Change does not match expected result", expResult, result, 0.01);
        } catch (Exception ex) {
            fail("Unexpected exception");
        }
    }

    @Test
    public void registerInsufficientPayment(){
        try{
            sale.addItem(1, 2);
            sale.endSale();
            sale.registerPayment(5);
            fail("Insufficient payment did not result in exception");
        } catch (InsufficientPaymentException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }
}