package model;

import integration.DBHandler;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.UnexpectedException;

import static org.junit.Assert.*;

public class TestRegisterPayment {
    private Sale sale;

    @Before
    public void setUp(){
        this.sale = new Sale(new DBHandler());
    }

    @After
    public void cleanUp(){
        this.sale = null;
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