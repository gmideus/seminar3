package controller;

import DTO.SaleDTO;
import integration.DBHandler;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import integration.NoSuchItemException;
import model.InsufficientPaymentException;
import model.InvalidArgumentException;
import model.Sale;

public class Controller {
    private Sale sale;
    private DBHandler dbHandler;

    public Controller(DBHandler dbHandler){
        this.dbHandler = dbHandler;
    }

    public void initiateSale(){
        this.sale = new Sale(dbHandler);
    }

    public boolean registerItem(int itemID, int quantity) throws DBUnavailableException, InvalidArgumentException {

        try {
            sale.addItem(itemID, quantity);
            return true;
        } catch (NoSuchItemException ex) {
            return false;
        }

    }

    public SaleDTO updateSale(){
        return sale.getSaleInfo();
    }

    public double completeSale(){
        return sale.endSale();
    }

    public double registerDisount(int customerID) throws DBUnavailableException, NoSuchCustomerException {
        return sale.addDiscount(customerID);
    }

    public double registerPayment(double payment) throws InsufficientPaymentException {
        return sale.registerPayment(payment);
    }
}
