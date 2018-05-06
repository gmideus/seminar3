package model;

import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.SaleDTO;
import DTO.SaleEntryDTO;
import integration.*;

public class Sale {
    private final static double TAX_RATE = 1.25;
    private final static int MIN_QUANTITY = 1;
    private ItemList itemList;
    private DBHandler dbHandler;
    private Printer printer;
    private double runningTotal;
    private double totalPrice;
    private double paidAmount;
    private double change;

    public Sale(DBHandler dbHandler){
        this.itemList = new ItemList();
        this.dbHandler = dbHandler;
        this.printer = new Printer();
        this.runningTotal = 0;
    }

    public void addItem(int itemID, int quantity) throws NoSuchItemException, DBUnavailableException, InvalidArgumentException {
        if(quantity < MIN_QUANTITY){
            throw new InvalidArgumentException();
        }
        ItemDTO item = dbHandler.getItem(itemID);
        itemList.addItem(item, quantity);
        updateRunningTotal(item, quantity);

    }

    public SaleDTO getSaleInfo(){
        return new SaleDTO(runningTotal, totalPrice, paidAmount, change, itemList.getSaleEntries());
    }

    private void updateRunningTotal(ItemDTO item, int quantity){
        this.runningTotal += (item.getPrice()*quantity);
    }

    public double endSale(){
        totalPrice = runningTotal*TAX_RATE;
        return totalPrice;
    }

    public double addDiscount(int customerID)throws DBUnavailableException, NoSuchCustomerException {
        CustomerDTO customer = dbHandler.getCustomer(customerID);
        totalPrice = totalPrice*customer.getDiscount();
        return totalPrice;
    }

    public double registerPayment(double payment) throws InsufficientPaymentException{
        if(payment < totalPrice){
            throw new InsufficientPaymentException();
        }
        change = payment - totalPrice;
        SaleDTO saleInfo = this.getSaleInfo();
        dbHandler.registerSale(saleInfo);
        printer.print(saleInfo);
        return change;
    }

}
