package repository;

import domain.BaseEntity;
import domain.exceptions.ValidatorException;

import java.io.Serializable;
import java.util.Optional;

public interface SortRepository<ID extends Serializable, T extends BaseEntity<ID>> extends Repository<ID, T> {


    /**
     * @return all entities.
     */
    Iterable<T> findAll(Sort sort);


}
