package run;

import collection.CollectionManager;
import collection.Person;
import commands.CommandInvoker;
import file.FileManager;
import file.PersonFieldsReader;
import file.XmlParser;
import io.UserIO;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.NoSuchElementException;

/**
 * Класс, через который запускается приложение
 */
public class Application {
    CollectionManager collectionManager;
    FileManager fileManager;
    XmlParser xmlParser;
    UserIO userIO;
    CommandInvoker commandInvoker;
    PersonFieldsReader personFieldsReader;

    public void start(String inputFile) {
        collectionManager = new CollectionManager();
        collectionManager.addOutputFile(inputFile);
        userIO = new UserIO();
        fileManager = new FileManager();
        xmlParser = new XmlParser();
        personFieldsReader = new PersonFieldsReader(userIO);
        commandInvoker = new CommandInvoker(collectionManager, userIO, inputFile, personFieldsReader);
        try {
            File inFile = new File(inputFile);
            if (!inFile.canWrite() || inFile.isDirectory() || !inFile.isFile()) throw new IOException();
            String fileString = fileManager.readFromFile(inputFile);
            Person[] persons = xmlParser.parseToCollection(new InputSource(new StringReader(fileString)));
            for (Person person : persons) {
                collectionManager.addToCollection(person);
            }
            userIO.printLine("Загружены элементы коллекции из указанного файла");
        } catch (ParserConfigurationException | IOException | SAXException ex) {
            System.err.println("По указанному пути нет подходящего файла");
            System.exit(0);
        }
        try {
            loop();
        } catch (NoSuchElementException ex) {
            System.err.println("Ctrl + D exception");
        }
    }

    public void loop() {
        userIO.printLine("Программа запущена");
        while (true) {
            userIO.printLine("Введите название команды");
            userIO.printSign();
            String line = userIO.readLine();
            commandInvoker.execute(line);
        }
    }
}
