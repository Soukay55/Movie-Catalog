/**
 * The MissingQuotesException class represents an exception that is thrown when there are missing quotes.
 * This exception extends the standard Exception class.
 */

/**
 * Wissem Oumsalem (40291712) <br>
 * Soukayna Haitami (40280964) <br>
 * COMP 249 <br>
 * Assignment #2 <br>
 * Due : March 27th, 2024
 *
 */
public class MissingQuotesException extends Exception {

    /**
     * Constructs a MissingQuotesException with a default error message.
     */
    public MissingQuotesException() {
        super("There are missing quotes");
    }

    /**
     * Constructs a MissingQuotesException with the specified error message.
     *
     * @param message The error message.
     */
    public MissingQuotesException(String message) {
        super(message);
    }
}

