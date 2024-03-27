/**
 * The BadTitleException class represents an exception that is thrown when an invalid title is encountered.
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

