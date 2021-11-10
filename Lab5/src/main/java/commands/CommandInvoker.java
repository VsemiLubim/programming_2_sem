package commands;

import collection.CollectionManager;
import file.PersonFieldsReader;
import io.UserIO;

import java.util.*;

/**
 * Класс, который выбирает команду и вызывает ее
 */
public class CommandInvoker {
    private Stack<String> history = new Stack<>();
    private TreeMap<String, Command> commands = new TreeMap<>();
    private static Set<String> scripts = new HashSet<>();
    private CollectionManager collectionManager;
    private UserIO userIO;
    private PersonFieldsReader personFieldsReader;
    private String outputFile;

    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, String outputFile, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.outputFile = outputFile;
        this.personFieldsReader = personFieldsReader;
        putCommands();
    }

    public CommandInvoker(CollectionManager collectionManager, UserIO userIO, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.userIO = userIO;
        this.personFieldsReader = personFieldsReader;
        putCommands();
    }

    public void putCommands() {
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, userIO, personFieldsReader));
        commands.put("update", new UpdateByIDCommand(collectionManager, userIO, personFieldsReader));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager, userIO, personFieldsReader));
        commands.put("clear", new ClearCommand(collectionManager, userIO));
        commands.put("save", new SaveCommand(collectionManager));
        commands.put("execute_script", new ExecuteScriptCommand(collectionManager, personFieldsReader));
        commands.put("exit", new ExitCommand());
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, userIO, personFieldsReader));
        commands.put("add_if_max", new AddIfMaxCommand(collectionManager, userIO, personFieldsReader));
        commands.put("history", new HistoryCommand(history));
        commands.put("remove_any_by_location", new RemoveAnyByLocationCommand(collectionManager, userIO, personFieldsReader));
        commands.put("average_of_height", new AverageOfHeightCommand(collectionManager, userIO));
        commands.put("count_less_than_hair_color", new CountLessThanHairColorCommand(collectionManager, userIO, personFieldsReader));
    }

    /**
     * Метод, который кладет в стек последнюю выполненную команду
     * @param command - имя команды и ее аргументы
     */
    public void pushHistory(String command) {
        if (history.size() > 11) {
            history.remove(history.size() - 1);
        }
        history.push(command.toLowerCase(Locale.ROOT));
    }

    /**
     * Метод, добавляющий название файла скрипта в множество
     * @param scriptName - имя файла скрипта
     */
    public static void addScript(String scriptName) {
        scripts.add(scriptName);
    }

    /**
     * Метод, убирающий название файла скрипта из множества
     * @param scriptName - имя файла скрипта
     */
    public static void removeScript(String scriptName) {
        scripts.remove(scriptName);
    }

    /**
     * Метод, проверяющий наличие файла скрипта в множестве
     * @param scriptName - имя файла скрипта
     */
    public static boolean checkScript(String scriptName){
        return scripts.contains(scriptName);
    }

    public void execute(String commandLine) {
        if (commandLine == null) {
            System.err.println("Введена пустая строка, завершение программы");
            System.exit(-1);
        }
        String[] commandWords = commandLine.trim().split("\\s+");
        String[] commandArgs = Arrays.copyOfRange(commandWords, 1, commandWords.length);
        Command command;
        if (commands.containsKey(commandWords[0].toLowerCase(Locale.ROOT).trim())) {
            command = commands.get(commandWords[0].toLowerCase(Locale.ROOT).trim());
            command.setArgs(commandArgs);
            pushHistory(commandLine);
            command.execute();
        } else {
            System.err.println("Неизвестная комманда " + commandWords[0]);
        }
    }

}
