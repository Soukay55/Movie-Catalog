/**
 * The BadYearException class represents an exception that is thrown when an invalid year is encountered.
 * This exception extends the standard Exception class.
 */
public class BadYearException extends Exception {

    /**
     * Constructs a BadYearException with a default error message.
     */
    public BadYearException() {
        super("The year entered is not valid");
    }

    /**
     * Constructs a BadYearException with the specified error message.
     *
     * @param message The error message.
     */
    public BadYearException(String message) {
        super(message);
    }
}

