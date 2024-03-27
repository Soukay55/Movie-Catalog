/**
 * The MissingFieldsException class represents an exception that is thrown when there are missing fields.
 * This exception extends the standard Exception class.
 */
public class MissingFieldsException extends Exception {

    /**
     * Constructs a MissingFieldsException with a default error message.
     */
    public MissingFieldsException() {
        super("There are missing fields");
    }

    /**
     * Constructs a MissingFieldsException with the specified error message.
     *
     * @param message The error message.
     */
    public MissingFieldsException(String message) {
        super(message);
    }
}

