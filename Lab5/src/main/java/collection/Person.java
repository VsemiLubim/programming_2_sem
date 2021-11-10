package collection;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TreeSet;

/**
 * Класс, хранящийся в коллекции
 */
public class Person implements Comparable<Person> {
    /**
     * Уникальный идентификатор объекта
     */
    private long id;
    private String name;
    private final Coordinates coordinates;
    private final ZonedDateTime creationDate;
    private int height;
    private LocalDateTime birthday;
    private Double weight;
    private Color hairColor;
    private Location location;
    private static final TreeSet<Long> uniqueIDSet = new TreeSet<>();

    public Person(String name, Coordinates coordinates, int height, LocalDateTime birthday, Double weight, Color hairColor, Location location) {
        long newId = 1;
        while (!uniqueIDSet.add(newId)) {
            newId++;
        }
        this.id = newId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
        this.location = location;
    }

    public Person(String name, Coordinates coordinates, ZonedDateTime creationDate, int height, LocalDateTime birthday, Double weight, Color hairColor, Location location) {
        long newId = 1;
        while (!uniqueIDSet.add(newId)) {
            newId++;
        }
        this.id = newId;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
        this.location = location;
    }

    public Person(long id, String name, Coordinates coordinates, int height, LocalDateTime birthday, Double weight, Color hairColor, Location location) {
        this.id = id;
        uniqueIDSet.add(id);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = ZonedDateTime.now();
        this.height = height;
        this.birthday = birthday;
        this.weight = weight;
        this.hairColor = hairColor;
        this.location = location;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public int getHeight() {
        return height;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public Double getWeight() {
        return weight;
    }

    public Color getHairColor() {
        return hairColor;
    }

    public Location getLocation() {
        return location;
    }


    public static void removeUniqueID(long id) {
        uniqueIDSet.remove(id);
    }

    public static boolean checkID(long id) {
        return uniqueIDSet.contains(id);
    }

    @Override
    public String toString() {
        String personString;
        try {
            personString = "Person:" +
                    "\n\tid: " + id +
                    "\n\tname: " + name +
                    "\n\tcoordinates: " + coordinates.toString() +
                    "\n\tcreationDate: " + creationDate.toString() +
                    "\n\theight: " + height +
                    "\n\tbirthday: " + birthday.toString() +
                    "\n\tweight: " + weight +
                    "\n\thairColor: " + hairColor.toString() +
                    "\n\tlocation: " + location.toString();
        } catch (NullPointerException ex) {
            if (location == null & birthday == null) {
                personString = "Person:" +
                        "\n\tid: " + id +
                        "\n\tname: " + name +
                        "\n\tcoordinates: " + coordinates.toString() +
                        "\n\tcreationDate: " + creationDate.toString() +
                        "\n\theight: " + height +
                        "\n\tbirthday: " + "not initialized" +
                        "\n\tweight: " + weight +
                        "\n\thairColor: " + hairColor.toString() +
                        "\n\tlocation: " + "not initialized";
            } else {
                if (location == null) {
                    personString = "Person:" +
                            "\n\tid: " + id +
                            "\n\tname: " + name +
                            "\n\tcoordinates: " + coordinates.toString() +
                            "\n\tcreationDate: " + creationDate.toString() +
                            "\n\theight: " + height +
                            "\n\tbirthday: " + birthday.toString() +
                            "\n\tweight: " + weight +
                            "\n\thairColor: " + hairColor.toString() +
                            "\n\tlocation: " + "not initialized";
                } else {
                    personString = "Person:" +
                            "\n\tid: " + id +
                            "\n\tname: " + name +
                            "\n\tcoordinates: " + coordinates.toString() +
                            "\n\tcreationDate: " + creationDate.toString() +
                            "\n\theight: " + height +
                            "\n\tbirthday: " + "not initialized" +
                            "\n\tweight: " + weight +
                            "\n\thairColor: " + hairColor.toString() +
                            "\n\tlocation: " + location.toString();
                }
            }
        }

        return personString;
    }

    @Override
    public int compareTo(Person person) {
        double thisWeightIndex = this.getWeight()/this.getHeight()/this.getHeight();
        double compWeightIndex = person.getWeight()/person.getHeight()/person.getHeight();
        if (thisWeightIndex == compWeightIndex){
            return 0;
        } else {
            if (thisWeightIndex > compWeightIndex){
                return 1;
            } else {
                return -1;
            }
        }
    }
}
