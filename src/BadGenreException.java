/**
 * The BadGenreException class represents an exception that is thrown when an invalid genre is encountered.
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

