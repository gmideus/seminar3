package model;

import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.SaleDTO;
import DTO.SaleEntryDTO;
import integration.*;

/**
 * Represents a sale of one or several items.
 */

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

    /**
     * Creates a new instance representing a new sale.
     *
     * @param dbHandler A DBHandler used to communicate with external databases.
     */

    public Sale(DBHandler dbHandler){
        this.itemList = new ItemList();
        this.dbHandler = dbHandler;
        this.printer = new Printer();
        this.runningTotal = 0;
    }

    /**
     * Adds one or more of a certain kind of item to the current sale.
     *
     * @param itemID An item ID used to identify the item to be added.
     * @param quantity Specifies the quantity of of the item to be added.
     * @throws NoSuchItemException Trows exception in case of an invalid item ID
     * @throws DBUnavailableException Throws exception in if the database cannot be reached.
     * @throws InvalidArgumentException Throws exception if an invalid quantity is given.
     */

    public void addItem(int itemID, int quantity) throws NoSuchItemException, DBUnavailableException, InvalidArgumentException {
        if(quantity < MIN_QUANTITY){
            throw new InvalidArgumentException();
        }
        ItemDTO item = dbHandler.getItem(itemID);
        itemList.addItem(item, quantity);
        updateRunningTotal(item, quantity);

    }

    /**
     * Creates and returns a new SaleDTO describing the sale in its current state
     * @return A new SaleDTO
     */
    public SaleDTO getSaleInfo(){
        return new SaleDTO(runningTotal, totalPrice, paidAmount, change, itemList.getSaleEntries());
    }

    private void updateRunningTotal(ItemDTO item, int quantity){
        this.runningTotal += (item.getPrice()*quantity);
    }

    /**
     * Ends the current sale to calculate the total price to be paid.
     * @return The total price after taxes.
     */
    public double endSale(){
        totalPrice = runningTotal*TAX_RATE;
        return totalPrice;
    }

    /**
     * Adds a discount given a customer ID.
     * @param customerID The customer ID to base the discount on.
     * @return The total price after the added discount
     * @throws DBUnavailableException Throws exception if the database can not be reached.
     * @throws NoSuchCustomerException Throws exception if the customer ID cannot be found in the database.
     */
    public double addDiscount(int customerID)throws DBUnavailableException, NoSuchCustomerException {
        CustomerDTO customer = dbHandler.getCustomer(customerID);
        totalPrice = totalPrice*customer.getDiscount();
        return totalPrice;
    }

    /**
     * Registers a payment for the current sale.
     * @param payment The amount paid by the customer
     * @return The change that should be given back to the customer.
     * @throws InsufficientPaymentException Throws exception if the payment is less than the total price.
     */
    public double registerPayment(double payment) throws InsufficientPaymentException{
        if(payment < totalPrice){
            throw new InsufficientPaymentException();
        }
        change = payment - totalPrice;
        paidAmount = payment;
        SaleDTO saleInfo = this.getSaleInfo();
        dbHandler.registerSale(saleInfo);
        printer.print(saleInfo);
        return change;
    }

}
