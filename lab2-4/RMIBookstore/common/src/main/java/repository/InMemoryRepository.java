package repository;

import domain.BaseEntity;
import domain.validators.Validator;
import domain.exceptions.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>
{

    private Map<ID, T> entities;
    private Validator<T> validator;



    public InMemoryRepository(Validator<T> validator)
    {
        this.entities = new HashMap<>();
        this.validator = validator;
    }

    @Override
    public Optional<T> findOne(ID id)
    {
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll()
    {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException
    {
        this.validator.validate(entity);
        return Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
    }

    @Override
    public Optional<T> delete(ID id)
    {

        return Optional.ofNullable(entities.remove(id));
    }

    //TO DO
    @Override
    public Optional<T> update(T entity) throws ValidatorException
    {
        System.out.println(validator);
        System.out.println(entity.toString());
        validator.validate(entity);
        //System.out.println("finish validate repo");
        return Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));

    }
}
