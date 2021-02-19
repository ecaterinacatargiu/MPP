package main.bookstore.domain.validators;

import main.bookstore.domain.Book;
import main.exceptions.ValidatorException;

import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Implementation of generic validator on the Book class.
 * Checks for null pointers, year of release to not be from the future and the ISBN to contain exactly 10 digits.
 *
 * @author Breje
 *
 */
public class BookValidator implements Validator<Book>
{
    @Override
    public void validate(Book entity) throws ValidatorException
    {
        boolean isNotNull = false;
        try
        {
            isNotNull = Stream.of(entity, entity.getID(), entity.getBID(), entity.getAuthor()).noneMatch(Objects::isNull);
        }
        catch(NullPointerException ignored)
        {
            assert true;
        }

        boolean validISBN = DoubleStream.of(entity.getBID()).filter(x -> x >= 1000000000.0 && x < 10000000000.0).findAny().isPresent();
        boolean isNotFromFuture = entity.getYear() < Year.now(ZoneId.systemDefault()).getValue() + 1;
        List<Boolean> adevarat = new ArrayList<>();
        adevarat.add(isNotNull);
        adevarat.add(validISBN);
        adevarat.add(isNotFromFuture);

        adevarat.stream().filter(x -> !x).findAny().ifPresent(a -> {throw new ValidatorException("Book is not valid");});
    }

}
