package view;

import controller.Controller;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import model.InsufficientPaymentException;
import model.InvalidArgumentException;

/**
 * Creates a new view that represents the user interface of the program.
 */
public class View {
    private Controller contr;
    private double runningTotal;
    private double totalPrice;

    public View(Controller contr){
        this.contr = contr;
    }

    public void runProgram() throws DBUnavailableException, NoSuchCustomerException, InvalidArgumentException {
        contr.initiateSale();
        System.out.println("Adding 2 valid items: "  + contr.registerItem(42, 2));
        runningTotal = contr.updateSale().getRunningTotal();
        System.out.println("Running Total: " + runningTotal);
        System.out.println("Adding an invalid item: " + contr.registerItem(13, 1));
        runningTotal= contr.updateSale().getRunningTotal();
        totalPrice = contr.completeSale();
        System.out.println("Total price including tax: " + totalPrice);
        totalPrice = contr.registerDiscount(0);
        System.out.println("Total price after discount: " + totalPrice);
        try{
            double change = contr.registerPayment(45);
            System.out.println("Change: " + change);
        } catch (InsufficientPaymentException ex){
            System.out.println("Insufficient payment");
        }


    }
}
