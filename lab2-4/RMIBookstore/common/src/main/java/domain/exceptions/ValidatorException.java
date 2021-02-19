package domain.exceptions;

/**
 * @author radu.
 */

public class ValidatorException extends domain.exceptions.BaseException
{
    public ValidatorException(String message)
    {
        super(message);
    }

    public ValidatorException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public ValidatorException(Throwable cause)
    {
        super(cause);
    }
}
