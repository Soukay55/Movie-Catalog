/**
 * The BadScoreException class represents an exception that is thrown when an invalid score is encountered.
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
public class BadScoreException extends Exception {

    /**
     * Constructs a BadScoreException with a default error message.
     */
    public BadScoreException() {
        super("This is not a valid score");
    }

    /**
     * Constructs a BadScoreException with the specified error message.
     *
     * @param message The error message.
     */
    public BadScoreException(String message) {
        super(message);
    }
}

