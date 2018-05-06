package model;

import integration.DBHandler;
import integration.DBUnavailableException;
import integration.NoSuchItemException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAddItem {
    private Sale sale;
    private DBHandler dbHandler;

    @Before
    public void setUp() {
        dbHandler = new DBHandler();
        sale = new Sale(dbHandler);
    }
    @Test
    public void addExistantItem() {
        try{
            double expResult = 46.4;
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
    public void addNegativeQuantity() {
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
}