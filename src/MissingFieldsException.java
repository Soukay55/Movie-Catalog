public class MissingFieldsException extends Exception{

    public MissingFieldsException()
    {
        super("There are missing fields");
    }

    public MissingFieldsException(String message)
    {
        super(message);
    }


}
