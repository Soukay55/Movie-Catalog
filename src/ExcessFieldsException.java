/**
 * The ExcessFieldsException class represents an exception that is thrown when excess fields are encountered.
 * This exception extends the standard Exception class.
 */
public class ExcessFieldsException extends Exception {

    /**
     * Constructs an ExcessFieldsException with a default error message.
     */
    public ExcessFieldsException() {
        super("There are excess fields");
    }

    /**
     * Constructs an ExcessFieldsException with the specified error message.
     *
     * @param message The error message.
     */
    public ExcessFieldsException(String message) {
        super(message);
    }
}

