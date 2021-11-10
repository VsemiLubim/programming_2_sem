package commands;

import collection.CollectionManager;
import exceptions.RecursiveScriptCallException;
import file.PersonFieldsReader;
import io.UserIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class ExecuteScriptCommand implements Command {
    CollectionManager collectionManager;
    PersonFieldsReader personFieldsReader;
    String fileName;
    String[] args;

    public ExecuteScriptCommand(CollectionManager collectionManager, PersonFieldsReader personFieldsReader) {
        this.collectionManager = collectionManager;
        this.personFieldsReader = personFieldsReader;
    }

    @Override
    public void execute() {
        try {
            if (args.length == 1) {
                fileName = args[0];
            } else {
                throw new java.lang.IllegalArgumentException();
            }
            File inFile = new File(fileName);
            if (!inFile.canWrite() || inFile.isDirectory() || !inFile.isFile()) throw new IOException();
            FileInputStream fileInputStream = new FileInputStream(inFile);
            Scanner scanner = new Scanner(fileInputStream);
            UserIO userIO = new UserIO(scanner);
            if (CommandInvoker.checkScript(fileName)) {
                throw new RecursiveScriptCallException();
            }
            CommandInvoker.addScript(fileName);
            CommandInvoker commandInvoker = new CommandInvoker(collectionManager, userIO, personFieldsReader);

            while (scanner.hasNextLine()) {
                commandInvoker.execute(scanner.nextLine());
            }
        } catch (FileNotFoundException ex) {
            System.err.println("Файл скрипта не неайден");
        } catch (IOException ex) {
            System.err.println("Доступ к файлу не возможен");
        } catch (IllegalArgumentException ex) {
            System.err.println("Ожидался один аргумент (id), введено " + args.length);
        } catch (RecursiveScriptCallException ex) {
            System.err.println("Скрипт " + fileName + " вызывается рекурсивно");
        }
        CommandInvoker.removeScript(fileName);
    }

    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String getDescription() {
        return "выполняет команды, описанные в файле";
    }
}
