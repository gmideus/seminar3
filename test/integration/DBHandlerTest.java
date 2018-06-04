package integration;

import DTO.CustomerDTO;
import DTO.ItemDTO;
import model.Sale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBHandlerTest {
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
    public void getNonExistentItem(){
        try{
            dbHandler.getItem(-1);
        } catch (NoSuchItemException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void getExistingCustomer() {
        try {
            CustomerDTO customer = dbHandler.getCustomer(1);
            int expResult = 1;
            assertEquals("Customer ID does not match the expected result", expResult, customer.getCustomerID());
        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void getNonExistentCustomer(){
        try{
            dbHandler.getCustomer(-1);
        } catch (NoSuchCustomerException ex){

        } catch (Exception ex){
            fail("Unexpected exception");
        }
    }

    @Test
    public void registerSale() {
        Sale sale = new Sale(this.dbHandler);
        try {
            dbHandler.registerSale(sale.getSaleInfo());
        } catch (Exception ex){
            fail("Unexpected exception when registering sale");
        }
    }
}