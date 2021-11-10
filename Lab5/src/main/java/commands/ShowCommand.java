package commands;

import collection.CollectionManager;

/**
 * Класс-команда, реализующий вывод полной информации о всех элементах коллекции
 */
public class ShowCommand implements Command {
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.show();
    }

    @Override
    public String getDescription() {
        return "комнда, которая выводит подробную информацию о элементах коллекции";
    }
}
