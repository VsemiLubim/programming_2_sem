package commands;

import collection.CollectionManager;
import collection.Person;
import exceptions.InvalidFieldException;
import exceptions.NoElementInCollection;
import file.PersonFieldsReader;
import io.UserIO;

/**
 * Класс-команда, реализующий обновление элемента по id
 */
public class UpdateByIDCommand implements Command {
    CollectionManager collectionManager;
    UserIO userIO;
    PersonFieldsReader personFieldsReader;
    String[] args;

    public UpdateByIDCommand(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        if (args.length == 1) {
            try {
                long id;
                id = Long.parseLong(args[0]);
                if (id <= 0) throw new InvalidFieldException();
                if (!Person.checkID(id)) throw new NoElementInCollection();
                Person.removeUniqueID(id);
                Person person = personFieldsReader.read(id);
                collectionManager.update(id, person);
            } catch (InvalidFieldException ex) {
                System.err.println("id должен быть больше 0");
            } catch (NoElementInCollection ex) {
                System.err.println("В коллекции нет элемента с таким id");
            } catch (NumberFormatException ex) {
                System.err.println("Введенный id должен быть типа long");
            }
        } else {
            System.err.println("Ожидался один аргумент (id), введено " + args.length);
        }

    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String getDescription() {
        return "изменяет элемент коллекции с определенным id";
    }
}
