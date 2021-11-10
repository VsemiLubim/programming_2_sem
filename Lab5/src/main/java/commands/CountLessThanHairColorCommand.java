package commands;

import collection.CollectionManager;
import collection.Color;
import file.PersonFieldsReader;
import io.UserIO;

/**
 * Класс-команда, реализующий подсчет элементов, значение поля hairColor которых меньше введенного
 */
public class CountLessThanHairColorCommand implements Command {
    CollectionManager collectionManager;
    UserIO userIO;
    PersonFieldsReader personFieldsReader;

    public CountLessThanHairColorCommand(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        userIO.printLine("Введите значение hairColor\n");
        Color hairColor = personFieldsReader.readHairColor();
        userIO.printLine("Количество элементов, у которых hairColor меньше введенного: " + collectionManager.countLessThanHairColor(hairColor));
    }

    @Override
    public String getDescription() {
        return "считает количество элементов в коллекции, значение hairColor которых меньше заданного(определяется по длине волны света[не знал, что придумать])";
    }
}
