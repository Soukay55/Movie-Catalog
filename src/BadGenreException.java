public class BadGenreException extends Exception{

    public BadGenreException()
    {
        super("This is not a valid genre");
    }

    public BadGenreException(String message)
    {
        super(message);
    }


}
