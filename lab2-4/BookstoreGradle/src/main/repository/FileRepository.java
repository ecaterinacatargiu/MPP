package main.repository;

import main.bookstore.domain.BaseEntity;
import main.bookstore.domain.validators.Validator;
import main.exceptions.ValidatorException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
import java.nio.file.*;

public class FileRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>
{

    private Map<ID, T> entities;
    private Validator<T> validator;
    private String filepath;

    public FileRepository(Validator<T> validator, String filePath)
    {
        this.entities = null;
        this.validator = validator;
        this.filepath = filePath;
        this.loadFromFile();
    }

    /**
     * Loads a map of entities from the text file via deserialization, if a file is present and has the right permissions.
     * If the file doesn't meet the requirements, a new map in instantiated.
     */
    private void loadFromFile()
    {
        Path file = Paths.get(this.filepath);
        boolean isRegularFile = Files.isRegularFile(file);
        boolean isReadable = Files.isReadable(file);
        boolean isWritable = Files.isWritable(file);
        boolean da = false;

        // If no file is present, try to create one
        if(!Files.exists(file))
        {
            try
            {
                Files.createFile(file);
            }
            catch(IOException ix)
            {
                this.entities = new HashMap<>();
                return;
            }
        }

        // If the file can't be accessed properly, ignore it and create a new map of entities
        if(!(isRegularFile & isReadable & isWritable))
        {
            this.entities = new HashMap<>();
            return;
        }

        try (FileInputStream inputStream = new FileInputStream(this.filepath);
             ObjectInputStream objectInputStream = new ObjectInputStream(inputStream))
        {
            this.entities = (Map<ID, T>) objectInputStream.readObject();

        }
        catch (ClassNotFoundException | IOException ex)
        {
            this.entities = new HashMap<>();
            ex.printStackTrace();
        }

    }

    /**
     * Saves the map of entities to a text file using serialization in order to maintain persistency.
     *
     * @throws IOException
     */
    public void saveToFile() throws IOException
    {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.filepath));
        try
        {
            objectOutputStream.writeObject(this.entities);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        objectOutputStream.close();
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
        Optional<T> rez = Optional.ofNullable(entities.putIfAbsent(entity.getID(), entity));
        rez.ifPresentOrElse(
                (value) -> {},

                () -> {
                try
                {
                    this.saveToFile();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        });
        return rez;
    }

    @Override
    public Optional<T> delete(ID id)
    {
        Optional<T> rez = Optional.ofNullable(entities.remove(id));
        rez.ifPresent(x -> {
            try
            {
                this.saveToFile();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        });
        return rez;
    }

    //TO DO
    @Override
    public Optional<T> update(T entity) throws ValidatorException
    {
        validator.validate(entity);
        Optional<T> rez = Optional.ofNullable(entities.computeIfPresent(entity.getID(), (k, v) -> entity));
        rez.ifPresentOrElse(
                (value) -> {},

                () -> {
                    try
                    {
                        this.saveToFile();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
        return rez;
    }
}
