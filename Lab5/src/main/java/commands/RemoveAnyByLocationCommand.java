package commands;

import collection.CollectionManager;
import collection.Location;
import file.PersonFieldsReader;
import io.UserIO;

/**
 * Класс-команда, реализующий уадление случайного элемента из коллекции, значение поля location которого совпадает с введенным
 */
public class RemoveAnyByLocationCommand implements Command {
    CollectionManager collectionManager;
    UserIO userIO;
    PersonFieldsReader personFieldsReader;

    public RemoveAnyByLocationCommand(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        userIO.printLine("Введите значение location\n");
        Location location = personFieldsReader.readLocation();
        collectionManager.removeAnyByLocation(location);
    }

    @Override
    public String getDescription() {
        return "удаляет случайный элемент из коллекции, если его location совпадает с введенным";
    }
}
