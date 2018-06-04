package model;

import DTO.ItemDTO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListEntryTest {
    ListEntry listEntry;

    @Before
    public void setUp() throws Exception {
        this.listEntry = new ListEntry(new ItemDTO("item", 1, 5, "test"), 1);
    }

    @After
    public void tearDown() throws Exception {
        this.listEntry = null;
    }

    @Test
    public void increaseQuantity() {
        try {
            listEntry.increaseQuantity(1);
            int expResult = 2;
            int result = listEntry.getQuantity();
            assertEquals("Item quantity does not match expected result", expResult, result);
        } catch (InvalidArgumentException ex){
            fail("Unexpected exception when increasing quantity");
        }
    }

    @Test
    public void increaseNegativeQuantity() {
        try {
            listEntry.increaseQuantity(-1);
            fail("Failed to throw exception when increasing with a negative number");
        } catch (InvalidArgumentException ex) {

        }

    }
}