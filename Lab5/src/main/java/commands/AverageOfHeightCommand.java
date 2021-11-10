package commands;

import collection.CollectionManager;
import io.UserIO;

/**
 * Класс-команда, реализующий подсчет среднего значения поля height элементов коллекции
 */
public class AverageOfHeightCommand implements Command {
    private CollectionManager collectionManager;
    private UserIO userIO;

    public AverageOfHeightCommand(CollectionManager collectionManager, UserIO userIO) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
    }

    @Override
    public void execute() {
        userIO.printLine("Среднее значение роста элементов равно: " + collectionManager.averageOfHeight());
    }

    @Override
    public String getDescription() {
        return "команда, которая возвращает среднее значение поля height всех элементов коллекции";
    }
}
