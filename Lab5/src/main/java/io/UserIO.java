package io;

import java.util.Scanner;

public class UserIO {
    Scanner scanner;

    public UserIO(Scanner scanner) {
        this.scanner = scanner;
    }

    public UserIO() {
        this.scanner = new Scanner(System.in, "UTF-8");
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void printLine(String line) {
        System.out.println(line);
    }

    public void printErrorMessage(String line) {
        System.err.println(line);
    }

    public void printSign() {
        System.out.print(">");
    }
}
