package model;

import DTO.ItemDTO;

/**
 * Represents an entry in an item list.
 */
public class ListEntry {
    ItemDTO item;
    int quantity;

    /**
     * Creates a new list entry
     * @param item An ItemDTO representing the item that shold be added.
     * @param quantity The quantity of the added item.
     */
    public ListEntry(ItemDTO item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of a specified item
     * @param quantity the quantity to be added.
     */
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
