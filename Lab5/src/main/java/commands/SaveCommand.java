package commands;

import collection.CollectionManager;

/**
 * Класс-команда, реализующий сохранение коллекции в файл
 */
public class SaveCommand implements Command {
    private CollectionManager collectionManager;

    public SaveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute() {
        collectionManager.save();
    }

    @Override
    public String getDescription() {
        return "сохраняет коллекцию в файл";
    }
}
