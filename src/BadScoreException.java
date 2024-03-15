public class BadScoreException extends Exception{

    public BadScoreException()
    {
        super("This is not a valid score");
    }

    public BadScoreException(String message)
    {
        super(message);
    }

}
