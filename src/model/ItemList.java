package model;

import DTO.ItemDTO;
import DTO.SaleEntryDTO;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<ListEntry> listEntries;

    public ItemList(){
        this.listEntries = new ArrayList<ListEntry>();

    }

    void addItem(ItemDTO item, int quantity){
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

    public SaleEntryDTO[] getSaleEntries(){
        SaleEntryDTO[] saleEntries = new SaleEntryDTO[listEntries.size()];
        for(int i = 0; i < listEntries.size(); i++){
            saleEntries[i] = new SaleEntryDTO(listEntries.get(i).getItem(), listEntries.get(i).getQuantity());
        }
        return saleEntries;
    }
}
