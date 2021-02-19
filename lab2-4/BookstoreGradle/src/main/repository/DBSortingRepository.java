package main.repository;

import main.bookstore.domain.BaseEntity;
import main.bookstore.domain.Client;
import main.bookstore.domain.validators.Validator;
import main.exceptions.ValidatorException;

import javax.swing.plaf.nimbus.State;
import java.io.Serializable;
import java.security.spec.ECField;
import java.sql.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DBSortingRepository<ID extends Serializable, T extends BaseEntity<ID>> implements SortRepository<ID, T>  {

    private Map<ID, T> entities;

    private Validator<T> validator;
    private String statement;
    private Function<Statement, T> getObjectFromDB;
    private Function<String, T> getInsertString;
    private Function<String, T> getDeleteString;
    private Function<String, T> getUpdateString;
    private Function<String, T> getSelectString;

    private static final String URL = "jdbc:postgresql://localhost:5432/bookPublisher";
    private static final String USER = System.getProperty("postgres");
    private static final String PASSWORD = System.getProperty("bunaprieteni");


    public DBSortingRepository(Validator<T> validator, String statement, Function<Statement, T> loadObjectFromDB, Function<String, T> getInsertString, Function<String, T> getDeleteString, Function<String, T> getUpdateString, Function<String, T> getSelectString) throws SQLException {
        this.entities = new HashMap<>();
        this.validator = validator;
        this.statement = statement;
        this.getObjectFromDB = loadObjectFromDB;
        this.getInsertString = getInsertString;
        this.getDeleteString = getDeleteString;
        this.getUpdateString = getUpdateString;
        this.getSelectString = getSelectString;

        this.loadObjectFromDB();
    }

    private void loadObjectFromDB() throws SQLException {

        System.out.println("Connecting to database..");
        try
        {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to database successfully");

            Statement stmt = connection.createStatement();
            T data = getObjectFromDB.apply(stmt);

            entities.put(data.getID(), data);
        }
        catch(SQLException sqlex)
        {
            sqlex.printStackTrace();
        }
    }



    @Override
    public Iterable<T> findAll(Sort sort) {
        List<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toList());
        Collections.sort(allEntities, sort);

        return allEntities;
    }

    @Override
    public Optional<T> findOne(ID id) {
        Optional<T> rez = Optional.ofNullable(entities.get(id));
        return rez;
    }

    @Override
    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException {

        //first save it memory and then to db
        this.validator.validate(entity);
        Optional<T> rez = Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
        rez.ifPresentOrElse(
                (value) -> {},
                () -> {
                    try
                    {
                        //save it into db
                        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Connected to database successfully");

                        T sql = getInsertString.apply(statement);
                        PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql));

                        preparedStatement.execute();
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                }
        );
        return rez;
    }

    @Override
    public Optional<T> delete(ID id) throws SQLException {

        Optional<T> rez = Optional.ofNullable(entities.remove(id));
        rez.ifPresent(x -> {
            try
            {
                Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Connected to database successfully");

                T sql = getDeleteString.apply(statement);
                PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql));

                preparedStatement.execute();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        });
        return rez;


    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, SQLException {

        validator.validate(entity);
        Optional<T> rez = Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));
        rez.ifPresentOrElse(
                (value) -> {},

                () -> {
                    try
                    {

                        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("Connected to database successfully");

                        T sql = getInsertString.apply(statement);
                        PreparedStatement preparedStatement = connection.prepareStatement(String.valueOf(sql));

                        preparedStatement.execute();

                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                });
        return  rez;
    }
}

