package integration;

public class NoSuchCustomerException extends Exception {
    /**
     * Creates a new exception
     */
    public NoSuchCustomerException(){
        super();
    }

    /**
     * Creates a new exception with a given message.
     * @param msg is the message to be attached to the exception.
     */
    public NoSuchCustomerException(String msg){
        super(msg);
    }
}
