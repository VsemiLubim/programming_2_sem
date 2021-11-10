package commands;

/**
 * Класс-команда, реализующий выход из прорграммы
 */
public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Завершение работы программы");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "команда, которая завершает раоту программы";
    }
}
