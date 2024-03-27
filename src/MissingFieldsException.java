/**
 * The MissingFieldsException class represents an exception that is thrown when there are missing fields.
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

