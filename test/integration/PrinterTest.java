package integration;

import model.Sale;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrinterTest {
    private Printer printer;

    @Before
    public void setUp(){
        this.printer = new Printer();
    }
    @After
    public void tearDown(){
        this.printer = null;
    }

    @Test
    public void print() {
        Sale sale = new Sale(new DBHandler());
        try {
            printer.print(sale.getSaleInfo());
        } catch (Exception ex){
            fail("Unexpected exception when printing receipt");
        }
    }
}