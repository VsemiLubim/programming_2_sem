package commands;

import collection.CollectionManager;

/**
 * Класс-команда, реализующий вывод информации о коллекции
 */
public class InfoCommand implements Command {
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.info();
    }

    @Override
    public String getDescription() {
        return "команда, которая выводит информацию о коллекции";
    }
}
