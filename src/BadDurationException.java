/**
 * The BadDurationException class represents an exception that is thrown when an invalid duration is encountered.
 * This exception extends the standard Exception class.
 */
public class BadDurationException extends Exception {

    /**
     * Constructs a BadDurationException with a default error message.
     */
    public BadDurationException() {
        super("This is not a valid duration");
    }

    /**
     * Constructs a BadDurationException with the specified error message.
     *
     * @param message The error message.
     */
    public BadDurationException(String message) {
        super(message);
    }
}

