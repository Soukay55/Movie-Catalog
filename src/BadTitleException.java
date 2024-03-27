/**
 * The BadTitleException class represents an exception that is thrown when an invalid title is encountered.
 * This exception extends the standard Exception class.
 */
public class BadTitleException extends Exception {

    /**
     * Constructs a BadTitleException with a default error message.
     */
    public BadTitleException() {
        super("This title is not valid");
    }

    /**
     * Constructs a BadTitleException with the specified error message.
     *
     * @param message The error message.
     */
    public BadTitleException(String message) {
        super(message);
    }
}

