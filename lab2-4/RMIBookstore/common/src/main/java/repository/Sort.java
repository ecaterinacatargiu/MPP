package repository;

import domain.Book;
import domain.Client;
import domain.Transaction;
import domain.exceptions.SortException;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;

public class Sort implements Comparator
{
    private String[] fields;
    private boolean direction;

    public static EnumSortDirection Direction;

    public Sort(EnumSortDirection direction, String ... fields)
    {
        this.direction = direction.getValue();
        this.fields = fields;
    }

    public Sort(String ... fields)
    {
        this.fields = fields;
    }

    private ArrayList<Integer> getResultArray(Class clasa, Object obj1, Object obj2) throws SortException
    {
        ArrayList<Integer> results = new ArrayList<>();
        ArrayList<String> trySuperclass = new ArrayList<>();
        ArrayList<Integer> nonUsedIndex = new ArrayList<>();
        int index = 0;
        boolean canAccess = false;
        try
        {
            for(String t : this.fields)
            {
                try
                {
                    Field theField = clasa.getDeclaredField(t);
                    canAccess = theField.isAccessible();
                    theField.setAccessible(true);
                    results.add(index, ((Comparable) theField.get(obj1)).compareTo(theField.get(obj2)));  // We are assuming any attribute of domain is of type Comparable
                    theField.setAccessible(canAccess);
                }
                catch (NoSuchFieldException exc)
                {
                    nonUsedIndex.add(index);
                    trySuperclass.add(t);
                }
                index++;
            }
            for(String t: trySuperclass)
            {
                try
                {
                    Field theSuperField = clasa.getSuperclass().getDeclaredField(t);
                    canAccess = theSuperField.isAccessible();
                    theSuperField.setAccessible(true);
                    results.add(nonUsedIndex.get(0),  ((Comparable) theSuperField.get(obj1)).compareTo(theSuperField.get(obj2)) ); // We are assuming any attribute of domain is of type Comparable
                    nonUsedIndex.remove(0);
                    theSuperField.setAccessible(canAccess);
                }
                catch (NoSuchFieldException nex)
                {
                    throw new SortException("No such field exists: "+ nex.getMessage());
                }
            }
        }
        catch(IllegalAccessException ex)
        {
            throw new SortException("Illegal access: " + ex.getMessage());
        }
        return results;
    }

    @Override
    public int compare(Object obj1, Object obj2) throws SortException
    {
        ArrayList<Integer> results = new ArrayList<Integer>();
        if(! obj1.getClass().equals(obj2.getClass()))
        {
            throw new SortException("Objects are not of same type");
        }
        if(obj1 instanceof Client)
        {
            try
            {
                Class clientClass = Class.forName("main.bookstore.domain.Client");
                results = getResultArray(clientClass, obj1, obj2);
            }
            catch (ClassNotFoundException ex)
            {
                throw new SortException(ex.getMessage());
            }
        }
        if(obj1 instanceof Book)
        {
            try
            {
                Class bookClass = Class.forName("main.bookstore.domain.Book");
                results = getResultArray(bookClass, obj1, obj2);
            }
            catch (ClassNotFoundException ex)
            {
                throw new SortException(ex.getMessage());
            }
        }
        if(obj1 instanceof Transaction)
        {
            try
            {
                Class transacClass = Class.forName("main.bookstore.domain.Transaction");
                results = getResultArray(transacClass, obj1, obj2);
            }
            catch (ClassNotFoundException ex)
            {
                throw new SortException(ex.getMessage());
            }
        }
        try
        {
            if(this.direction)
            {
                return -(results.stream().filter(x -> x!=0).findFirst().orElseThrow(Exception::new));
            }
            else
            {
                return results.stream().filter(x -> x!=0).findFirst().orElseThrow(Exception::new);
            }
        }
        catch(Exception ex)
        {
            return 0;
        }
    }
}
