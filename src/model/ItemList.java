package model;

import DTO.ItemDTO;
import DTO.SaleEntryDTO;

import java.util.ArrayList;

/**
 * An item list that can contain several entries representing the items that have been added to a sale.
 */
public class ItemList {
    private ArrayList<ListEntry> listEntries;

    /**
     * Creates a new empty list
     */
    public ItemList(){
        this.listEntries = new ArrayList<ListEntry>();

    }

    /**
     * Adds one or more items to a list. If the list already contains a certain item, the entry is instead updated
     * @param item An ItemDTO representing the item that should be added
     * @param quantity The quantity of the item that should be added.
     */
    void addItem(ItemDTO item, int quantity) throws InvalidArgumentException{
        if(quantity < 0){
            throw new InvalidArgumentException();
        }
        ListEntry entry = this.getEntry(item);
        if(entry != null){
            entry.increaseQuantity(quantity);
        }else {
            listEntries.add(new ListEntry(item, quantity));
        }
    }


    private ListEntry getEntry(ItemDTO item){
        for(int i = 0; i < listEntries.size(); i++){
            if(listEntries.get(i).getItem().getItemID() == item.getItemID()){
                return listEntries.get(i);
            }
        }
        return null;
    }

    /**
     * Creates a list of SaleEntryDTOs based on the current item list.
     * @return An array of SaleEntryDTOs.
     */
    public SaleEntryDTO[] getSaleEntries(){
        SaleEntryDTO[] saleEntries = new SaleEntryDTO[listEntries.size()];
        for(int i = 0; i < listEntries.size(); i++){
            saleEntries[i] = new SaleEntryDTO(listEntries.get(i).getItem(), listEntries.get(i).getQuantity());
        }
        return saleEntries;
    }
}
