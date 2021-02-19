package domain.xml_domain;

import domain.BaseEntity;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLBaseEntity<ID> extends BaseEntity<ID>
{
    public static Element getXMLElement(Document doc, BaseEntity baseEntity)
    {
        return doc.createElement("base");
    }
}
