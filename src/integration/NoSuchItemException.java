package integration;

public class NoSuchItemException extends Exception {
    /**
     * Creates a new exception
     */
    public NoSuchItemException(){
        super();
    }

    /**
     * Creates a new exception with a given message
     * @param msg is the message to be attached to the exception
     */
    public NoSuchItemException(String msg){
        super(msg);
    }
}
