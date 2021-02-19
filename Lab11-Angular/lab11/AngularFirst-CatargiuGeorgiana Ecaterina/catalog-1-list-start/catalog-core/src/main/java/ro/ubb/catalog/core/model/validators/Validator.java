package ro.ubb.catalog.core.model.validators;

import ro.ubb.catalog.core.exceptions.ValidatorException;

/**
 * Interface for generic validations
 *
 * @author Breje
 */
public interface Validator<T>
{
    void validate(T entity) throws ValidatorException;
}