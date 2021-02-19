package main;

public class CustomNetworkException extends RuntimeException
{

    public CustomNetworkException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public CustomNetworkException(Throwable cause)
    {
        super(cause);
    }
}
