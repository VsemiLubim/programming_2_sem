package commands;

import collection.CollectionManager;
import collection.Person;
import file.PersonFieldsReader;
import io.UserIO;

/**
 * Класс-команда, реализующая добавление элемента в коллекцию
 */
public class AddCommand implements Command {
    private CollectionManager collectionManager;
    private UserIO userIO;
    private PersonFieldsReader personFieldsReader;

    public AddCommand(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        userIO.printLine("Введите значения элемента коллекции\n");
        Person person = personFieldsReader.read();
        collectionManager.addToCollection(person);
    }

    @Override
    public String getDescription() {
        return "добавляет элемент в коллекцию";
    }
}
