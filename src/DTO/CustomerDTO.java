package DTO;

public class CustomerDTO {
    String name;
    String address;
    int customerID;
    double discount;


    public CustomerDTO(String name, String address, int customerID, double discount){
        this.name = name;
        this.address = address;
        this.customerID = customerID;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCustomerID() {
        return customerID;
    }

    public double getDiscount() {
        return discount;
    }

}
