package collection;

/**
 * Класс, поле класса Person
 */
public class Location {
    private Float x;
    private Integer y;
    private Double z;
    private String name;

    public Location(Float x, Integer y, Double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    public Float getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

    public String getName() {
        return name;
    }

    public boolean comparisonOfLocations(Location location) {
        return (this.getX().equals(location.getX()) & this.getY().equals(location.getY()) & this.getZ().equals(location.getZ()) & this.getName().equals(location.getName()));
    }

    @Override
    public String toString() {
        if (name != null) {
            return "\n\t\tx: " + x +
                    "\n\t\ty: " + y +
                    "\n\t\tz: " + z +
                    "\n\t\tname: " + name;
        } else {
            return "\n\t\tx: " + x +
                    "\n\t\ty: " + y +
                    "\n\t\tz: " + z +
                    "\n\t\tname: " + "not initialized";
        }
    }
}
