package DTO;

public class SaleDTO {
    private double runningTotal;
    private double totalPrice;
    private double paidAmount;
    private double change;
    private SaleEntryDTO[] saleEntries;

    public SaleDTO(double runningTotal, double totalPrice, double paidAmount, double change, SaleEntryDTO[] saleEntries){
        this.runningTotal = runningTotal;
        this.totalPrice = totalPrice;
        this.paidAmount = paidAmount;
        this.change = change;
        this.saleEntries = saleEntries;
    }

    public double getRunningTotal() {
        return runningTotal;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public double getChange() {
        return change;
    }

    public SaleEntryDTO[] getSaleEntries(){
        return saleEntries;
    }
}
