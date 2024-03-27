/**
 * The BadGenreException class represents an exception that is thrown when an invalid genre is encountered.
 * This exception extends the standard Exception class.
 */
public class BadGenreException extends Exception {

    /**
     * Constructs a BadGenreException with a default error message.
     */
    public BadGenreException() {
        super("This is not a valid genre");
    }

    /**
     * Constructs a BadGenreException with the specified error message.
     *
     * @param message The error message.
     */
    public BadGenreException(String message) {
        super(message);
    }
}

