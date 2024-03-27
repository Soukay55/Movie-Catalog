/**
 * The BadNameException class represents an exception that is thrown when an invalid name is encountered.
 * This exception extends the standard Exception class.
 */
public class BadNameException extends Exception {

    /**
     * Constructs a BadNameException with a default error message.
     */
    public BadNameException() {
        super("This is not a valid name");
    }

    /**
     * Constructs a BadNameException with the specified error message.
     *
     * @param message The error message.
     */
    public BadNameException(String message) {
        super(message);
    }
}

