package view;

import DTO.SaleDTO;
import model.SaleObserver;

public class TotalRevenueView implements SaleObserver {
    private double totalRevenue;

    /**
     * Creates a new TotalRevenueView and sets the initial revenue to 0.
     */
    public TotalRevenueView(){
        totalRevenue = 0;
    }

    /**
     * Used to update the total revenue once a sale has been completed.
     * @param sale A DTO containing information about the sale.
     */
    public void newSale(SaleDTO sale){
        totalRevenue += sale.getTotalPrice();
        printCurrentState();
    }

    private void printCurrentState(){
        System.out.println("Current total revenue is: " + totalRevenue);
    }
}
