package model;

import DTO.ItemDTO;

public class ListEntry {
    ItemDTO item;
    int quantity;

    public ListEntry(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    public void increaseQuantity(int quantity){
        this.quantity += quantity;
    }

    public ItemDTO getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
