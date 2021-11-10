package collection;

/**
 * Перечисление, поле класса Person
 */
public enum Color {
    GREEN(2),
    RED(5),
    BLUE(1),
    YELLOW(3),
    BROWN(4);
    private final int id;

    Color(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
