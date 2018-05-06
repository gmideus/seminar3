package integration;

import DTO.CustomerDTO;
import DTO.ItemDTO;
import DTO.SaleDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class DBHandler {
    private final String ITEM_DB_NAME = "itemdb";
    private final String CUSTOMER_DB_NAME = "customerdb";
    private final char NEW_FIELD_CHAR = ';';
    private final int ID_INDEX = 0;

    public DBHandler(){

    }


    public ItemDTO getItem(int itemID) throws NoSuchItemException, DBUnavailableException{

        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ITEM_DB_NAME));
            String line;
            while((line = bufferedReader.readLine()) != null){
                ArrayList<String> lineEntries = readDBLine(line);
                int entryID = Integer.parseInt(lineEntries.get(ID_INDEX));
                if(entryID == itemID){
                    return new ItemDTO(lineEntries.get(1), entryID, Double.parseDouble(lineEntries.get(2)), lineEntries.get(3));
                }
            }
            throw new NoSuchItemException();
        } catch (IOException ex) {
            throw new DBUnavailableException();
        }

    }

    public CustomerDTO getCustomer(int customerID) throws DBUnavailableException, NoSuchCustomerException{
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(CUSTOMER_DB_NAME));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                ArrayList<String> lineEntries = readDBLine(line);
                int entryID = Integer.parseInt(lineEntries.get(ID_INDEX));
                if(entryID == customerID){
                    return new CustomerDTO(lineEntries.get(1), lineEntries.get(2), entryID, Double.parseDouble(lineEntries.get(3)));
                }
            }
            throw new NoSuchCustomerException();

        } catch (IOException ex){
            throw new DBUnavailableException();
        }

    }

    public void registerSale(SaleDTO sale){

    }

    private ArrayList<String> readDBLine(String line){
        ArrayList<String> dbContent = new ArrayList<String>();
        int i = 0;
        while(i < line.length()){
            StringBuilder stringBuilder = new StringBuilder();
            while(i < line.length() && line.charAt(i) != NEW_FIELD_CHAR){
                stringBuilder.append(line.charAt(i));
                i++;
            }
            i++;
            dbContent.add(stringBuilder.toString());
        }
        return dbContent;
    }
}
