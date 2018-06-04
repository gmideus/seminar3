package model;

import DTO.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemListTest {
    ItemList itemList;

    @Before
    public void setUp() throws Exception {
        this.itemList = new ItemList();
    }

    @After
    public void tearDown() throws Exception {
        this.itemList = null;
    }

    @Test
    public void addItem() {
        try {
            itemList.addItem(new ItemDTO("item", 1, 5, "test"), 5);
        } catch (Exception ex){
            fail("Unexpected exception when adding item");
        }
    }

    @Test
    public void addDuplicateItem(){
        try{
            itemList.addItem(new ItemDTO("item", 1, 5, "test"), 1);
            itemList.addItem(new ItemDTO("item", 1, 5, "test"), 1);
            int expResult = 2;
            int result = itemList.getSaleEntries()[0].getQuantity();
            assertEquals("Quantity of item does not match expected result", expResult, result);
        } catch (Exception ex){
            fail("Unexpected exception when adding duplicate items.");
        }
    }

    @Test
    public void addNegativeQuantityItem(){
        try {
            itemList.addItem(new ItemDTO("item", 1, 5, "test"), -2);
            fail("addItem failed to throw exception when adding negative quantity.");
        } catch (InvalidArgumentException ex){

        } catch (Exception ex){
            fail("Unexpected exception when adding negative quantity");
        }
    }
}