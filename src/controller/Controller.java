package controller;

import DTO.SaleDTO;
import integration.DBHandler;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import integration.NoSuchItemException;
import model.InsufficientPaymentException;
import model.InvalidArgumentException;
import model.Sale;
import model.SaleObserver;

import java.util.ArrayList;

/**
 * A controller used by the user interface to communicate with the rest of the program.
 */
public class Controller {
    private Sale sale;
    private DBHandler dbHandler;
    private ArrayList<SaleObserver> saleObservers = new ArrayList<>();

    /**
     * Creates a new controller
     * @param dbHandler DBHandler used to communicate with external databases.
     */
    public Controller(DBHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    /**
     * Starts a new sale
     */
    public void initiateSale(){
        this.sale = new Sale(dbHandler);
        this.sale.addSaleObservers(saleObservers);
    }

    /**
     * Registers one or more items with a given item ID to the current sale.
     * @param itemID ID of the item that should be added.
     * @param quantity Quantity of the item that should be added.
     * @return Returns true if the item was successfully added or false if the ID is invalid.
     * @throws DBUnavailableException Exceåption if the database could not be reached.
     * @throws InvalidArgumentException Esception if the quantity is invalid.
     */
    public boolean registerItem(int itemID, int quantity) throws  InvalidArgumentException {

        try {
            sale.addItem(itemID, quantity);
            return true;
        } catch (NoSuchItemException ex) {
            return false;
        }

    }

    /**
     * Updates information for the current sale
     * @return A SaleDTO describing the sale in its current state
     */
    public SaleDTO updateSale(){
        return sale.getSaleInfo();
    }

    /**
     * Ends the current sale
     * @return The total price to be paid.
     */
    public double completeSale(){
        return sale.endSale();
    }

    /**
     * Adds a discount given a specific customer ID
     * @param customerID Customer ID used to get information about the discount.
     * @return  The updated total price after discount.
     * @throws DBUnavailableException Exception if the database is unavailable.
     * @throws NoSuchCustomerException Exception if the customer ID cannot be found in the database.
     */
    public double registerDisount(int customerID) throws NoSuchCustomerException {
        return sale.addDiscount(customerID);
    }

    /**
     * Registers a payment made by the customer.
     * @param payment The amount paid.
     * @return The change to be given back to the customer.
     * @throws InsufficientPaymentException Exception if the payment is less than the total cost.
     */
    public double registerPayment(double payment) throws InsufficientPaymentException {
        return sale.registerPayment(payment);
    }

    /**
     * Adds an observer used to notify the view about sale information
     * @param observer the observer to be added to the controller.
     */
    public void addSaleObserver(SaleObserver observer){
        saleObservers.add(observer);
    }
}
