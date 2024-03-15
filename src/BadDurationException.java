public class BadDurationException extends Exception{

    public BadDurationException()
    {
        super("This is not a valid duration");
    }

    public BadDurationException(String message)
    {
        super(message);
    }
}
