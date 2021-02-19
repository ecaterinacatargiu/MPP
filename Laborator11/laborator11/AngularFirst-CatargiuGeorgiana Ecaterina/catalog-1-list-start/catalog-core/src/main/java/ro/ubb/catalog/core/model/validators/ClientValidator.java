package ro.ubb.catalog.core.model.validators;


import ro.ubb.catalog.core.model.Client;
import ro.ubb.catalog.core.exceptions.ValidatorException;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.Date;


/**
 * Implementation of generic validator on the Client class.
 * Checks for null pointers, for a valid age range (year of birth > 01.01.1900 and < current date), gender in a pre-determined list.
 * Also checks if the CNP contains all 13 digits.
 *
 * @author Breje
 *
 */
public class ClientValidator implements Validator<Client>
{
    @Override
    public void validate(Client entity) throws ValidatorException
    {
        boolean isNotNull = false;
        try
        {
            isNotNull = Stream.of(entity.getId(), entity.getCNP(), entity.getGender(), entity.getYob()).noneMatch(Objects::isNull);
        }
        catch(NullPointerException ignored)
        {
            assert true;
        }

        boolean isBetweenDates = !entity.getYob().before(Date.from(LocalDate.of(1900, Month.JANUARY, 1).atStartOfDay(ZoneId.systemDefault()).toInstant())) &&
                                    !entity.getYob().after(new Date());
        List<String> genderList = new ArrayList<>();
        genderList.add("female");
        genderList.add("male");
        genderList.add("apache");
        boolean isBinary = genderList.stream().anyMatch(n -> n.equals(entity.getGender()));
        boolean isCNPvalid = DoubleStream.of(entity.getCNP()).filter(x -> x>= 1000000000000.0 && x < 10000000000000.0 ).findAny().isPresent();

        List<Boolean> finale = new ArrayList<>();
        finale.add(isNotNull);
        finale.add(isBetweenDates);
        finale.add(isBinary);
        finale.add(isCNPvalid);
        finale.stream().filter(x -> !x).findAny().ifPresent(a -> { throw new ValidatorException("Client is not valid"); });
    }

}
