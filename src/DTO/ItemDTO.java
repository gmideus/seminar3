package DTO;

public class ItemDTO {
    private String name;
    private int itemID;
    private String description;
    private double price;

    public ItemDTO(String name, int itemID, double price, String description){
        this.name = name;
        this.itemID = itemID;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getItemID() {
        return itemID;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }
}
