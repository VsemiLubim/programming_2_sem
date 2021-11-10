package commands;

import java.util.Stack;

/**
 * Класс-команда, реализующий вывод последних 12 выполненных команд
 */
public class HistoryCommand implements Command {
    private Stack<String> history;

    public HistoryCommand(Stack<String> history) {
        this.history = history;
    }

    @Override
    public void execute() {
        for (int i = history.size() - 1; i >= 0; i--) {
            System.out.println(history.get(i));
        }
    }

    @Override
    public String getDescription() {
        return "команда, которая выводит последние введенные 12 команд";
    }
}
