package collection;

/**
 * Класс, поле класса Person
 */
public class Coordinates {
    private Long x;
    private double y;

    public Coordinates(Long x, double y) {
        this.x = x;
        this.y = y;
    }

    public Long getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "\n\t\tx: " + x +
                "\n\t\ty: " + y;
    }
}
