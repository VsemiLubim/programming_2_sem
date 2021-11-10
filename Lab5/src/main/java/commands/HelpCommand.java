package commands;

import java.util.Map;
import java.util.TreeMap;

/**
 * Класс-команда, реализующий вывод информации о всех доступных командах
 */
public class HelpCommand implements Command {
    private TreeMap<String, Command> treeMap;

    public HelpCommand(TreeMap<String, Command> treeMap) {
        this.treeMap = treeMap;
    }

    @Override
    public void execute() {
        for (Map.Entry<String, Command> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().getDescription());
        }
    }

    @Override
    public String getDescription() {
        return "команда, которая выводит информацию о всех доступных командах";
    }
}
