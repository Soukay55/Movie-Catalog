public class BadTitleException extends Exception {

    public BadTitleException()
    {
        super("This title is not valid");
    }

    public BadTitleException(String message)
    {
        super(message);
    }
}
