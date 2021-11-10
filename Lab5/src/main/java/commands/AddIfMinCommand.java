package commands;

import collection.CollectionManager;
import collection.Person;
import file.PersonFieldsReader;
import io.UserIO;

/**
 * Класс-команда, реализующий добавление элемента в коллекцию, если он наименьший
 */
public class AddIfMinCommand implements Command {
    CollectionManager collectionManager;
    UserIO userIO;
    PersonFieldsReader personFieldsReader;

    public AddIfMinCommand(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        userIO.printLine("Введите значения элемента коллекции\n");
        Person person = personFieldsReader.read();
        collectionManager.addIfMin(person);
    }

    @Override
    public String getDescription() {
        return "добавляет элемент в коллекцию, если его индекс массы weight/height^2 самый маленький среди коллекции";
    }
}
