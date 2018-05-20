package model;

import DTO.SaleDTO;

public interface SaleObserver {

    void newSale(SaleDTO sale);
}
