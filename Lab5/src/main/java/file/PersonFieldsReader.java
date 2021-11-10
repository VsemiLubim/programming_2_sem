package file;

import collection.Color;
import collection.Coordinates;
import collection.Location;
import collection.Person;
import exceptions.InvalidFieldException;
import exceptions.NoElementInCollection;
import io.UserIO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Класс, производящий чтение полей элемента
 */
public class PersonFieldsReader {
    private UserIO userIO;

    public PersonFieldsReader(UserIO userIO) {
        this.userIO = userIO;
    }

    public long readId() {
        Long id;
        while (true) {
            try {
                userIO.printLine("id(long, >0, which collection contains): ");
                id = Long.parseLong(userIO.readLine());
                if (id <= 0) throw new InvalidFieldException();
                if (!Person.checkID(id)) throw new NoElementInCollection();
                return id;
            } catch (InvalidFieldException ex) {
                System.err.println("id должен быть больше 0");
            } catch (NoElementInCollection ex) {
                System.err.println("В коллекции нет элемента с таким id");
            } catch (NumberFormatException ex) {
                System.err.println("Введенный id должен быть типа long");
            }

        }
    }

    public String readName() {
        String str;
        while (true) {
            userIO.printLine("name(String, not Null): ");
            str = userIO.readLine().trim();
            if (str.equals("")) {
                userIO.printErrorMessage("\nЗначение имени не может быть пустой строкой");
            } else {
                return str;
            }
        }
    }

    public Coordinates readCoordinates() {
        return new Coordinates(readCoordinatesX(), readCoordinateY());
    }

    public Long readCoordinatesX() {
        Long x;
        while (true) {
            try {
                userIO.printLine("coordinate_x (long, not Null, <=543): ");
                x = Long.parseLong(userIO.readLine().trim());
                if (x > 543) throw new InvalidFieldException();
                else return x;
            } catch (InvalidFieldException ex) {
                System.err.println("Координата x должна быть не больше 543");
            } catch (NumberFormatException ex) {
                System.err.println("Введенное число должно быть типа long и not null");
            }
        }
    }

    public double readCoordinateY() {
        double y;
        while (true) {
            try {
                userIO.printLine("coordinate_y (double, not Null, y <=246): ");
                y = Double.parseDouble(userIO.readLine().trim());
                if (y > 246) throw new InvalidFieldException();
                else return y;
            } catch (InvalidFieldException ex) {
                System.err.println("Координата y должна быть не больше 246");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типа double");
            }
        }
    }

    public int readHeight() {
        int height;
        while (true) {
            try {
                userIO.printLine("height (int, >0, not Null): ");
                height = Integer.parseInt(userIO.readLine());
                if (height <= 0) throw new InvalidFieldException();
                else return height;
            } catch (InvalidFieldException ex) {
                System.err.println("Рост должен быть больше нуля");
            } catch (NumberFormatException ex) {
                System.err.println("Значение должно быть типа int");
            }
        }
    }

    public LocalDateTime readBirthday() {
        LocalDateTime birthday;
        DateTimeFormatter birthdayFormat = DateTimeFormatter.ofPattern("d MM yyyy HH:mm:ss");
        while (true) {
            try {
                userIO.printLine("birthday (format: d MM yyyy HH:mm:ss, can be Null): ");
                String str = userIO.readLine().trim();
                if (str.equals("")) {
                    birthday = null;
                } else {
                    birthday = LocalDateTime.parse(str, birthdayFormat);
                }
                return birthday;
            } catch (DateTimeParseException ex) {
                System.err.println("Формат даты должен иметь вид d MM yyyy HH:mm:ss");
            }
        }
    }

    public Double readWeight() {
        Double weight;
        while (true) {
            try {
                userIO.printLine("weight (not Null, double, >0): ");
                weight = Double.parseDouble(userIO.readLine().trim());
                if (weight <= 0) throw new InvalidFieldException();
                else return weight;
            } catch (InvalidFieldException ex) {
                System.err.println("Вес должен быть больше нуля");
            } catch (NumberFormatException ex) {
                System.err.println("Число должно быть типом double и not null");
            }
        }
    }

    public Color readHairColor() {
        Color hairColor;
        while (true) {
            try {
                userIO.printLine("Допустимые значения hairColor: \n");
                for (Color color : Color.values()) {
                    userIO.printLine(color.name() + "\n");
                }
                userIO.printLine("hairColor: ");
                hairColor = Color.valueOf(userIO.readLine().toUpperCase().trim());
                return hairColor;
            } catch (IllegalArgumentException ex) {
                System.err.println("Значение введенной константы не представлено в перечислении");
            }
        }
    }

    public Location readLocation() {
        Location location;
        while (true) {
            userIO.printLine("location (if want null, write empty line, else write anything): ");
            if (userIO.readLine().trim().equals("")) {
                location = null;
                return location;
            } else {
                location = new Location(readLocationX(), readLocationY(), readLocationZ(), readLocationName());
                return location;
            }
        }
    }

    public Float readLocationX() {
        Float location_x;
        while (true) {
            try {
                userIO.printLine("location_x (float, not Null): ");
                location_x = Float.parseFloat(userIO.readLine().trim());
                return location_x;
            } catch (NumberFormatException ex) {
                System.err.println("Введенное число должно быть типа float и not null");
            }
        }
    }

    public Integer readLocationY() {
        Integer location_y;
        while (true) {
            try {
                userIO.printLine("location_y (integer, not null): ");
                location_y = Integer.parseInt(userIO.readLine().trim());
                return location_y;
            } catch (NumberFormatException ex) {
                System.err.println("Введенное число должно быть типа int и not null");
            }
        }
    }

    public Double readLocationZ() {
        Double location_z;
        while (true) {
            try {
                userIO.printLine("location_z (double, not Null): ");
                location_z = Double.parseDouble(userIO.readLine().trim());
                return location_z;
            } catch (NumberFormatException ex) {
                System.err.println("Введенное число должно быть типа double и not null");
            }
        }
    }

    public String readLocationName() {
        String location_name;
        while (true) {
            userIO.printLine("location_name (String, can be Null): ");
            String str = userIO.readLine().trim();
            if (str.equals("")) {
                return null;
            } else {
                location_name = str;
                return location_name;
            }
        }
    }

    public Person read() {
        return new Person(readName(), readCoordinates(), readHeight(), readBirthday(), readWeight(), readHairColor(), readLocation());
    }

    public Person read(long id) {
        return new Person(id, readName(), readCoordinates(), readHeight(), readBirthday(), readWeight(), readHairColor(), readLocation());
    }
}
