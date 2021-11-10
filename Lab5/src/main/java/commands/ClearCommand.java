package commands;

import collection.CollectionManager;
import io.UserIO;

/**
 * Класс-команда, реализующий очистку коллекции
 */
public class ClearCommand implements Command {
    private CollectionManager collectionManager;
    private UserIO userIO;

    public ClearCommand(CollectionManager collectionManager, UserIO userIO) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    @Override
    public void execute() {
        collectionManager.clear();
        userIO.printLine("Коллекция очищена");
    }

    @Override
    public String getDescription() {
        return "команда, которая очищает коллекцию";
    }
}
