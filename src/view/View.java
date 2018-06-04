package view;

import controller.Controller;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import integration.NoSuchItemException;
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
        contr.addSaleObserver(new TotalRevenueView());
    }

    public void runProgram(){
        contr.initiateSale();
        try{
            System.out.println("Adding 2 valid items:");
            contr.registerItem(42, 2);
            System.out.println("Done");
        } catch (InvalidArgumentException ex){
            System.out.println("Invalid quantity");
        } catch (NoSuchItemException ex){
            System.out.println("Item " + ex.getItemID() + " could not be found.");
        }
        runningTotal = contr.updateSale().getRunningTotal();
        System.out.println("Running Total: " + runningTotal);
        try{
            System.out.println("Adding an invalid item:");
            contr.registerItem(13, 1);
            System.out.println("Done");
        } catch (InvalidArgumentException ex){
            System.out.println("Invalid quantity");
        } catch (NoSuchItemException ex){
            System.out.println("Item " + ex.getItemID() + " could not be found.");
        }

        runningTotal= contr.updateSale().getRunningTotal();
        totalPrice = contr.completeSale();
        System.out.println("Total price including tax: " + totalPrice);
        try {
            totalPrice = contr.registerDisount(0);
            System.out.println("Total price after discount: " + totalPrice);
        } catch (NoSuchCustomerException ex) {
            System.out.println(ex.getMessage());
        }

        try{
            double change = contr.registerPayment(45);
            System.out.println("Change: " + change);

        } catch (InsufficientPaymentException ex){
            System.out.println("Insufficient payment");
        }



    }
}
