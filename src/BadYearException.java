public class BadYearException extends Exception{

    public BadYearException()
    {
        super("The year entered is not valid");
    }

    public BadYearException(String message)
    {
        super(message);
    }
}
