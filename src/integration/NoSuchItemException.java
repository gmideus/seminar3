package integration;

public class NoSuchItemException extends Exception {
    private int itemID;
    /**
     * Creates a new exception
     */
    public NoSuchItemException(){
        super();
    }

    /**
     * Creates a new exception with a given message
     * @param msg is the message to be attached to the exception
     * @param itemID is the ID of the item that could not be found.
     */
    public NoSuchItemException(String msg, int itemID){
        super(msg);
        this.itemID = itemID;
    }

    public int getItemID(){
        return this.itemID;
    }
}
