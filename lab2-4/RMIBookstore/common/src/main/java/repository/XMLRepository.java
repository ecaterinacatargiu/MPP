package repository;

import domain.BaseEntity;
import domain.validators.Validator;
import domain.exceptions.ValidatorException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.nio.file.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class XMLRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T>
{

    private Map<ID, T> entities;
    private Validator<T> validator;
    private String filepath;
    private String className;
    private BiFunction<Document, T, Element> makeXML;
    private Function<Element, T> loadObjectFunction;

    public XMLRepository(Validator<T> validator, String filePath, String type, BiFunction<Document, T, Element> makeXML, Function<Element, T> loadObject)
    {
        this.entities = new HashMap<>();
        this.validator = validator;
        this.filepath = filePath;
        this.className = type;
        this.makeXML = makeXML;
        this.loadObjectFunction = loadObject;
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
                this.initializeXML();
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

        try
        {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(this.filepath);

            NodeList nList = doc.getElementsByTagName(className);

            Stream<Node> nodeStream = IntStream.range(0, nList.getLength())
                    .mapToObj(nList::item);

            nodeStream.filter(
                    x -> x.getNodeType() == Node.ELEMENT_NODE
            ).forEach(
                    y ->
                    {
                        this.save(this.loadObjectFunction.apply((Element)y));
                    }
            );

        }
        catch (ParserConfigurationException  e)
        {
            e.printStackTrace();
        }
        catch(SAXException | IOException px)
        {
            this.initializeXML();
        }
    }


    public void initializeXML()
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("publisher");
            doc.appendChild(rootElement);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(this.filepath));

            transformer.setOutputProperty(OutputKeys.METHOD, "html");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void removeNodeFromXML(ID id)
    {
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.filepath);
            Element root = document.getDocumentElement();

            NodeList nList = document.getElementsByTagName(this.className);
            Stream<Node> nodeStream = IntStream.range(0, nList.getLength())
                    .mapToObj(nList::item);


            nodeStream.filter(
                    x -> x != null && x.getNodeType()==Node.ELEMENT_NODE && ((Element)x).getAttribute("id").equals(id.toString())
            ).forEach(root::removeChild);


            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.filepath));

            transformer.transform(source, result);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    /**
     * Saves one entity to a xml file by appending it.
     *
     * @throws IOException
     */
    public void saveToFileOne(T object) throws IOException
    {
        try
        {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(this.filepath);
            Element root = document.getDocumentElement();

            try
            {
                if(!(root.getTagName().toLowerCase().equals("publisher")))
                {
                    this.initializeXML();
                }
            }
            catch(Exception ex)
            {
                this.initializeXML();
            }

            AtomicBoolean ok = new AtomicBoolean(true);

            //Element newNode = object.getXMLElement(document);
            Element newNode = this.makeXML.apply(document, object);

            NodeList nList = document.getElementsByTagName(this.className);
            Stream<Node> nodeStream = IntStream.range(0, nList.getLength())
                    .mapToObj(nList::item);

            nodeStream.filter(
                    x -> x.getNodeType() == Node.ELEMENT_NODE
            ).forEach(
                    x -> {
                        if(((Element) x).getAttribute("id").equals(newNode.getAttribute("id")))
                        {
                            ok.set(false);
                        }
                    }
            );

            if(!ok.get())
            {
                return;
            }
            root.appendChild(newNode);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(this.filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            transformer.transform(source, result);

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
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
                        this.saveToFileOne(entity);
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
                this.removeNodeFromXML(id);
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
                        this.removeNodeFromXML(entity.getID());
                        this.saveToFileOne(entity);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                });
        return rez;
    }
}
