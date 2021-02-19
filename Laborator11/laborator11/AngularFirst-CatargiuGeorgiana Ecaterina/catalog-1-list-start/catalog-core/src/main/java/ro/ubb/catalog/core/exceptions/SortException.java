package ro.ubb.catalog.core.exceptions;


public class SortException extends RuntimeException{

    public SortException(String message)
    {
        super(message);
    }

    public SortException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public SortException(Throwable cause)
    {
        super(cause);
    }
}
