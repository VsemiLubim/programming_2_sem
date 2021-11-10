package collection;

import file.FileManager;
import file.XmlParser;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * Менеджер коллекции - класс, отвечающий за работу с коллекцией
 */
public class CollectionManager {
    private final HashSet<Person> hashSet;
    private final ZonedDateTime collectionInitializationTime;
    private final TreeSet<Long> uniqueIdSet = new TreeSet<>();
    private String outputFile;

    public CollectionManager() {
        this.hashSet = new HashSet<>();
        this.collectionInitializationTime = ZonedDateTime.now();
    }

    /**
     * Метод, выводящий информацию о коллекции
     */
    public void info() {
        System.out.println("Тип коллекции: " + hashSet.getClass().getSimpleName());
        System.out.println("Тип элементов коллекции: " + Person.class.getSimpleName());
        System.out.println("Количество элементов в коллекции: " + hashSet.size());
        System.out.println("Время инициализации коллекции: " + collectionInitializationTime.toString());
    }

    /**
     * Метод, выводящий полную информацию о элементах коллекции
     */
    public void show() {
        if (hashSet.size() == 0) {
            System.out.println("Коллекция пуста");
        } else {
            for (Person person : hashSet) {
                System.out.println(person.toString());
            }
        }
    }

    /**
     * Метод, добавляющии в коллекцию элемент
     * @param person - объект класса Person, элемент коллекции
     */
    public void addToCollection(Person person) {
        if (hashSet.add(person)) {
            uniqueIdSet.add(person.getId());
            System.out.println("Элемент доавлен в коллекцию");
        } else {
            System.out.println("Такой элемент существует в коллекции");
        }
    }

    /**
     * Метод, обновляющий элемент в коллекции
     * @param id - идентивикатор элемента
     * @param person - объект класса Person, элемент коллекции
     */
    public void update(long id, Person person) {
        removeById(id);
        addToCollection(person);
        System.out.println("Элемент с таким id обновлен");
    }

    /**
     * удаление элемента из коллекции, по id
     * @param id - идентификатор элемента
     */
    public void removeById(long id) {
        if (uniqueIdSet.remove(id)) {
            for (Person person : hashSet) {
                if (person.getId() == id) {
                    hashSet.remove(person);
                    removeUniqueID(id);
                    Person.removeUniqueID(id);
                    break;
                }
            }
        } else {
            System.out.println("Элемента с таким id нет в коллекции");
        }
    }

    /**
     * Метод, очищающий коллекцию
     */
    public void clear() {
        hashSet.clear();
        uniqueIdSet.clear();
    }

    public void addOutputFile(String outputFile) {
        this.outputFile = outputFile;
    }

    /**
     * Метод, возвращающий среднее значение поля height всех элементов коллекции
     * @return - среднее значение height
     */
    public float averageOfHeight() {
        if (hashSet.size() == 0) {
            return 0;
        } else {
            int size = hashSet.size();
            float averageHeight = 0;
            for (Person person : hashSet) {
                averageHeight += person.getHeight();
            }
            return averageHeight / size;
        }
    }

    /**
     * Метод, считающий количество элементов в коллекции, у которых значение поля hairColor меньше переданного
     * @param color - значение hairColor
     * @return - количество таких элементов
     */
    public int countLessThanHairColor(Color color) {
        int colorId = color.getId();
        int counter = 0;
        for (Person person : hashSet) {
            if (person.getHairColor().getId() < colorId) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Метод, удаляющий случайный элемент из коллекции, у которого поле location совпадает с переданным
     * @param location - значение location
     */
    public void removeAnyByLocation(Location location) {
        boolean executionFlag = false;
        if (location == null) {
            for (Person person : hashSet) {
                if (person.getLocation() == null) {
                    executionFlag = hashSet.remove(person);
                    removeUniqueID(person.getId());
                    Person.removeUniqueID(person.getId());
                    break;
                }
            }
        } else {
            try {
                for (Person person : hashSet) {
                    if (person.getLocation().comparisonOfLocations(location)) {
                        executionFlag = hashSet.remove(person);
                        removeUniqueID(person.getId());
                        Person.removeUniqueID(person.getId());
                        break;
                    }
                }
            } catch (NullPointerException ignored) {

            }
        }
        if (executionFlag) {
            System.out.println("Элемент с такими значениями Locations был удален");
        } else {
            System.out.println("Элемент с такими значениями Locations не найден в коллекции");
        }
    }

    /**
     * Метод, добавляющий в коллекцию элемент, если его индекс массы наименьший
     * @param person - объект класса Person, элемент коллекции
     */
    public void addIfMin(Person person) {
        double weightIndex = person.getWeight() / person.getHeight() / person.getHeight();
        if (hashSet.size() != 0) {
            Iterator<Person> iterator = hashSet.iterator();
            Person p = iterator.next();
            double minWeightIndex = p.getWeight() / p.getHeight() / p.getHeight();
            while (iterator.hasNext()) {
                p = iterator.next();
                if (minWeightIndex > p.getWeight() / p.getHeight() / p.getHeight()) {
                    minWeightIndex = p.getWeight() / p.getHeight() / p.getHeight();
                }
            }
            if (weightIndex < minWeightIndex) {
                System.out.println("Элемент является наименьшим");
                addToCollection(person);
            } else {
                System.out.println("Элемент не является наименьшим, поэтому не добавлен в коллекцию");
            }
        } else {
            addToCollection(person);
        }
    }

    /**
     * Метод, добавляющий в коллекцию элемент, если его индекс массы наибольший
     * @param person - объект класса Person, элемент коллекции
     */
    public void addIfMax(Person person) {
        double weightIndex = person.getWeight() / person.getHeight() / person.getHeight();
        if (hashSet.size() != 0) {
            double maxWeightIndex = 0;
            for (Person p : hashSet) {
                if (maxWeightIndex < p.getWeight() / p.getHeight() / p.getHeight()) {
                    maxWeightIndex = p.getWeight() / p.getHeight() / p.getHeight();
                }
            }
            if (maxWeightIndex < weightIndex) {
                System.out.println("Элемент является наибольшим");
                addToCollection(person);
            } else {
                System.out.println("Элемент не является наибольшим, поэтому не добавлен в коллекцию");
            }
        } else {
            addToCollection(person);
        }
    }

    /**
     * Метод, сохраняющий элементы коллекции в файл
     */
    public void save() {
        XmlParser xmlParser = new XmlParser();
        FileManager fileManager = new FileManager();
        Person[] persons;
        persons = hashSet.toArray(new Person[hashSet.size()]);
        String str = xmlParser.parseToXml(persons);
        fileManager.writeToFile(str, outputFile);
        System.out.println("Коллекция сохранена в файл");
    }

    public void removeUniqueID(long id) {
        uniqueIdSet.remove(id);
    }
}
