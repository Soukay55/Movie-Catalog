/**
 * The ExcessFieldsException class represents an exception that is thrown when excess fields are encountered.
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

