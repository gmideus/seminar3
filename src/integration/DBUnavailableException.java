package integration;

public class DBUnavailableException extends RuntimeException {
    /**
     * Creates a new exception
     */
    public DBUnavailableException(){
        super();
    }

    /**
     * Creates a new exception with a given message.
     * @param msg is the message to be attached to the exception
     */
    public DBUnavailableException(String msg){
        super(msg);
    }

    /**
     * Creates a new exception with a message and a cause in the form of another exception that caused this exception to be thrown.
     * @param msg is the message to be attached to the exception
     * @param cause is the exception that caused this exception to be thrown.
     */
    public DBUnavailableException(String msg, Exception cause){
        super(msg, cause);
    }
}
