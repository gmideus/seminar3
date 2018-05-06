package model;

import integration.DBHandler;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestEndSale {
    private Sale sale;
    private DBHandler dbHandler;

    @Before
    public void setUp(){
        dbHandler = new DBHandler();
        sale = new Sale(dbHandler);
        try {
            sale.addItem(1, 1);
            sale.addItem(1, 1);
        } catch (Exception ex) {
            fail("Unexpected exception adding items");
        }
    }

    @Test
    public void endSale() {
        double expResult = 12.5;
        double result = sale.endSale();
        assertEquals("endSale does not return the expected total price", expResult, result, 0.01);
    }
}