package domain.validators;

import domain.exceptions.ValidatorException;

/**
 * Interface for generic validations
 *
 * @author Breje
 */
public interface Validator<T>
{
    void validate(T entity) throws ValidatorException;
}