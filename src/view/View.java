package view;

import controller.Controller;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import model.InsufficientPaymentException;
import model.InvalidArgumentException;

public class View {
    private Controller contr;
    private double totalPrice;

    public View(Controller contr){
        this.contr = contr;
    }

    public void testProgram() throws DBUnavailableException, NoSuchCustomerException, InvalidArgumentException {
        contr.initiateSale();
        System.out.println(contr.registerItem(42, 3));
        totalPrice = contr.updateSale().getRunningTotal();
        System.out.println(totalPrice);
        System.out.println(contr.registerItem(13, 1));
        totalPrice = contr.updateSale().getRunningTotal();
        totalPrice = contr.completeSale();
        System.out.println(totalPrice);
        totalPrice = contr.registerDisount(0);
        System.out.println(totalPrice);
        try{
            double change = contr.registerPayment(45);
            System.out.println(change);
        } catch (InsufficientPaymentException ex){
            System.out.println("Insufficient payment");
        }


    }
}
