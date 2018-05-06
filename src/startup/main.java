package startup;

import controller.Controller;
import integration.DBHandler;
import integration.DBUnavailableException;
import integration.NoSuchCustomerException;
import model.InvalidArgumentException;
import view.View;

public class main {

    public static void main(String[] args){
        DBHandler dbHandler = new DBHandler();
        Controller contr = new Controller(dbHandler);
        View view = new View(contr);
        try{
            view.runProgram();
        } catch (DBUnavailableException ex){
            System.out.println("DB error");
        } catch (NoSuchCustomerException ex){
            System.out.println("Invalid Customer ID");
        } catch (InvalidArgumentException ex){
            System.out.println("Invalid argument");
        }

    }
}
