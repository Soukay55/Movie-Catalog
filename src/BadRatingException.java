/**
 * The BadRatingException class represents an exception that is thrown when an invalid rating is encountered.
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
public class BadRatingException extends Exception {

    /**
     * Constructs a BadRatingException with a default error message.
     */
    public BadRatingException() {
        super("This is not a valid rating");
    }

    /**
     * Constructs a BadRatingException with the specified error message.
     *
     * @param message The error message.
     */
    public BadRatingException(String message) {
        super(message);
    }
}

