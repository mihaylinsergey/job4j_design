package ru.job4j.serialization.json;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "item")
@XmlAccessorType(XmlAccessType.FIELD)
public class Item {
    @XmlAttribute
    private boolean available;

    @XmlAttribute
    private int id;
    private Title title;
    @XmlElementWrapper(name = "accessories")
    @XmlElement(name = "accessories")
    private String[] accessories;

    public Item() {
    }

    public Item(boolean available, int iD, Title title, String[] accessories) {
        this.available = available;
        this.id = iD;
        this.title = title;
        this.accessories = accessories;
    }

    public boolean isAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public String[] getAccessories() {
        return accessories;
    }

    @Override
    public String toString() {
        return "Item{"
              + "available=" + available
              +  ", iD=" + id
              +  ", title=" + title
              +  ", accessories=" + Arrays.toString(accessories)
              +  '}';
    }

    public static void main(String[] args) throws Exception {
        Item example = new Item(true, 123456, new Title("smartphone"),
                new String[] {"headphone", "case"});
        JAXBContext context = JAXBContext.newInstance(Item.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(example, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Item result = (Item) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
