public class ExcessFieldsException extends Exception{

    public ExcessFieldsException()
    {
        super("There are excess fields");
    }

    public ExcessFieldsException(String message)
    {
        super(message);
    }
}
