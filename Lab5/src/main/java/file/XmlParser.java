package file;

import collection.*;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * Класс, производящий парсинг данных
 */
public class XmlParser {
    public Person[] parseToCollection(InputSource text) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XmlHandler handler = new XmlHandler();
        try {
            parser.parse(text, handler);
        } catch (SAXException ignored) {
        }
        Person[] personArr = new Person[handler.persons.size()];
        return handler.persons.toArray(personArr);
    }

    public String parseToXml(Person[] persons) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version = \"1.0\"?>\n");
        sb.append("<hashset>\n");
        for (Person person : persons) {
            sb.append("\t<person>\n");
            sb.append("\t\t<name>").append(person.getName()).append("</name>");
            sb.append("\n\t\t<coordinates_x>").append(person.getCoordinates().getX()).append("</coordinates_x>");
            sb.append("\n\t\t<coordinates_y>").append(person.getCoordinates().getY()).append("</coordinates_y>");
            sb.append("\n\t\t<creation_date>").append(person.getCreationDate()).append("</creation_date>");
            sb.append("\n\t\t<height>").append(person.getHeight()).append("</height>");
            try {
                sb.append("\n\t\t<birthday>").append(person.getBirthday()).append("</birthday>");
            } catch (NullPointerException isNull) {
            }
            sb.append("\n\t\t<weight>").append(person.getWeight()).append("</weight>");
            sb.append("\n\t\t<haircolor>").append(person.getHairColor()).append("</haircolor>");
            try {
                float checkNotNull = person.getLocation().getX();
                sb.append("\n\t\t<location_x>").append(checkNotNull).append("</location_x>");
                sb.append("\n\t\t<location_y>").append(person.getLocation().getY()).append("</location_y>");
                sb.append("\n\t\t<location_z>").append(person.getLocation().getZ()).append("</location_z>");
                sb.append("\n\t\t<location_name>").append(person.getLocation().getName()).append("</location_name>");
            } catch (NullPointerException isNull) {
            }
            sb.append("\n\t</person>\n");
        }
        sb.append("</hashset>\n");
        return sb.toString();
    }

    private static class XmlHandler extends DefaultHandler {
        private ArrayList<Person> persons = new ArrayList<>();
        private String name;
        private Long coordinates_x = null;
        private Double coordinates_y = null;
        private ZonedDateTime creationDate = null;
        private Integer height = null;
        private LocalDateTime birthday = null;
        private Double weight = null;
        private Color hairColor = null;
        private Float location_x = null;
        private Integer location_y = null;
        private Double location_z = null;
        private String location_name;
        private String lastElementName;

        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
        }

        public void characters(char[] ch, int start, int length) throws ClassCastException {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();
            try {
                if (!information.isEmpty()) {
                    if (lastElementName.equals("name"))
                        name = information;
                    else if (lastElementName.equals("coordinates_x"))
                        coordinates_x = Long.parseLong(information);
                    else if (lastElementName.equals("coordinates_y"))
                        coordinates_y = Double.parseDouble(information);
                    else if (lastElementName.equals("creation_date"))
                        creationDate = ZonedDateTime.parse(information);
                    else if (lastElementName.equals("height"))
                        height = Integer.parseInt(information);
                    else if (lastElementName.equals("birthday")){
                        if (information.equals("null")){
                            birthday = null;
                        } else {
                            birthday = LocalDateTime.parse(information);
                        }
                    }
                    else if (lastElementName.equals("weight"))
                        weight = Double.parseDouble(information);
                    else if (lastElementName.equals("haircolor"))
                        hairColor = Color.valueOf(information);
                    else if (lastElementName.equals("location_x"))
                        location_x = Float.parseFloat(information);
                    else if (lastElementName.equals("location_y"))
                        location_y = Integer.parseInt(information);
                    else if (lastElementName.equals("location_z"))
                        location_z = Double.parseDouble(information);
                    else if (lastElementName.equals("location_name")){
                        if (information.equals("null")){
                            location_name = null;
                        } else {
                            location_name = information;
                        }
                    }
                }
            } catch (IllegalArgumentException ex) {
                System.err.println("Указанной константы перечисляемого типа не существует, либо невозможно преобразование типов");
            }
        }

        public void endElement(String uri, String localName, String qname) {
            if (qname.equals("person")) {
                if ((name != null && !name.isEmpty()) && (coordinates_x != null && coordinates_x <= 543) && (coordinates_y != null && coordinates_y <= 246) && (height != null && height > 0) && (weight != null && weight > 0) && (hairColor != null) && ((location_x == null && location_y == null && location_z == null) || (location_x != null && location_y != null && (location_z != null)))) {
                    double doubleY = coordinates_y;
                    Coordinates coordinates = new Coordinates(coordinates_x, doubleY);
                    if (creationDate == null) {
                        creationDate = ZonedDateTime.now();
                    }
                    Location location;
                    if (location_x == null) {
                        location = null;
                    } else {
                        location = new Location(location_x, location_y, location_z, location_name);
                    }
                    Person person = new Person(name, coordinates, creationDate, height, birthday, weight, hairColor, location);
                    persons.add(person);
                } else {
                    System.err.println("Указаны не все параметры или они не принадлежат ОДЗ");
                }
                name = null;
                coordinates_x = null;
                coordinates_y = null;
                creationDate = null;
                height = null;
                birthday = null;
                weight = null;
                hairColor = null;
                location_x = null;
                location_y = null;
                location_z = null;
                location_name = null;
            }
        }
    }
}
