package main.bookstore.domain.validators;

import main.service.BookPublisher;
import main.exceptions.ValidatorException;

import java.time.LocalDate;
import java.time.Month;
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
 * @author Catargiu Georgiana Ecaterina
 *
 */

public class BookPublisherValidator implements Validator<BookPublisher> {

    @Override
    public void validate(BookPublisher entity) throws ValidatorException {

        boolean isNotNull = false;
        try
        {
            isNotNull = Stream.of(entity, entity.getBPID(), entity.getDateTransaction(), entity.getClientService(), entity.getBookService()).noneMatch(Objects::isNull);
        }
        catch(NullPointerException ignored)
        {
            assert true;
        }

        boolean validBPID = DoubleStream.of(entity.getBPID()).filter(x -> x >= 1000000000.0 && x < 10000000000.0).findAny().isPresent();
        boolean isBetweenDates = !entity.getDateTransaction().before(Date.from(LocalDate.of(1900, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())) &&
                !entity.getDateTransaction().after(new Date());

        List<Boolean> trueValues = new ArrayList<>();
        trueValues.add(validBPID);
        trueValues.add(isBetweenDates);

        trueValues.stream().filter(x -> !x).findAny().ifPresent(a -> {throw new ValidatorException("Book is not valid");});



    }
}


