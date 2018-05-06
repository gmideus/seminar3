package DTO;

public class SaleEntryDTO {
    private ItemDTO itemDTO;
    private int quantity;

    public SaleEntryDTO(ItemDTO itemDTO, int quantity){
        this.itemDTO = itemDTO;
        this.quantity = quantity;
    }

    public ItemDTO getItemDTO() {
        return itemDTO;
    }

    public int getQuantity() {
        return quantity;
    }
}
