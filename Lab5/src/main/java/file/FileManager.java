package file;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Класс, отвечающий за чтение/запись данных из файла/в файл
 */
public class FileManager {
    public String readFromFile(String filePath) {
        Scanner scanner = null;
        StringBuilder sb = new StringBuilder();
        try {
            scanner = new Scanner(Paths.get(filePath));
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
            }
        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлени файла во входящий поток");
            System.exit(-1);
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл, из которого следует читать");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return sb.toString();
    }

    public void writeToFile(String str, String filePath) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(filePath);
            printWriter.write(str);
        } catch (IOException ex) {
            System.err.println("Произошла ошибка при добавлении файла в исходящий поток");
        } catch (NullPointerException ex) {
            System.err.println("Не указан файл, куда следует записать данные1");
        } finally {
            if (printWriter != null) {
                printWriter.close();
            }
        }
    }
}