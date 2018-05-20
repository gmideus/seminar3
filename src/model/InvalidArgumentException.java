package model;

public class InvalidArgumentException extends Exception {
    /**
     * Creates a new exception
     */
    public  InvalidArgumentException(){
        super();
    }

    /**
     * Creates a new exception with a given message
     * @param msg is the message to be attached to the exception.
     */
    public InvalidArgumentException(String msg){
        super(msg);
    }
}

