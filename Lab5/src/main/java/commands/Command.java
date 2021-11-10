package commands;

public interface Command {
    void execute();

    default void setArgs(String[] args) {
    }

    default String getDescription() {
        return "Пока что нет описания данной команды";
    }
}
