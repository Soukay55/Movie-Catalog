public class MissingQuotesException extends Exception{

    public MissingQuotesException()
    {
        super("There are missing quotes");
    }

    public MissingQuotesException(String message)
    {
        super(message);
    }
}
