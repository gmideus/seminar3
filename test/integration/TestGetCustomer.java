package integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import DTO.CustomerDTO;

import static org.junit.Assert.*;

public class TestGetCustomer {
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
            CustomerDTO customer = dbHandler.getCustomer(1);
            int expResult = 1;
            assertEquals("Customer ID does not match the expected result", expResult, customer.getCustomerID());
        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void getNonexistentItem(){
        try{
            dbHandler.getCustomer(-1);
        } catch (NoSuchCustomerException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }
}