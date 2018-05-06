package model;

import integration.DBHandler;
import integration.NoSuchCustomerException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddDiscount {
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
}