package run;

public class Lab5 {
    public static void main(String[] args) {
        if (args.length > 0) {
            if (!args[0].equals("")) {
                Application application = new Application();
                application.start(args[0]);
            } else {
                System.err.println("Не введен путь к файлу, из которого надо читать коллекцию");
                System.exit(-1);
            }
        } else {
            System.err.println("Не введен путь к файлу, из которого надо читать коллекцию");
            System.exit(-1);
        }
    }
}
