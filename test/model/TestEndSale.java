package model;

import integration.DBHandler;
import org.junit.After;
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
    }

    @After
    public void cleanUp(){
        sale = null;
        dbHandler = null;
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
}