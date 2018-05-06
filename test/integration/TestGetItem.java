package integration;

import DTO.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestGetItem {
    private DBHandler dbHandler;

    @Before
    public void setUp(){
        this.dbHandler = new DBHandler();
    }

    @After
    public void cleanUp(){
        this.dbHandler = null;
    }

    @Test
    public void getExistingItem() {
        try {
            ItemDTO item = dbHandler.getItem(1);
            int expResult = 1;
            assertEquals("Item ID does not match the expected result", expResult, item.getItemID());
        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void getNonexistentItem(){
        try{
            dbHandler.getItem(-1);
        } catch (NoSuchItemException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }
}