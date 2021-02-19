package domain;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.Serializable;
import java.util.Optional;

public class BaseEntity<ID> implements Serializable
{
    private ID id;

    public ID getID()
    {
        return this.id;
    }

    public void setID(ID newID)
    {
        this.id = newID;
    }

    @Override
    public String toString() {
        return "BaseEntity {" + " id=" + id + " }";
    }


/*
    public BaseEntity<ID> createFromXMLElement(Element xml)
    {
        return new BaseEntity<>();
    }*/
}
