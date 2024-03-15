public class BadRatingException extends Exception{

    public BadRatingException()
    {
        super("This is not a valid rating");

    }

    public BadRatingException(String message)
    {
        super(message);

    }
}
