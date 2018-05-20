package model;

public class InsufficientPaymentException extends Exception {
    /**
     * Creates a new InsufficientPaymentException
     */
    public InsufficientPaymentException(){
        super();
    }

    /**
     * Creates a new exception with a given message
     * @param msg is the message to be attached to the exception
     */
    public InsufficientPaymentException(String msg){
        super(msg);
    }
}
