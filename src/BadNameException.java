public class BadNameException extends Exception{

    public BadNameException()
    {
        super("This is not a valid name");
    }

    public BadNameException(String message)
    {
        super(message);
    }
}
