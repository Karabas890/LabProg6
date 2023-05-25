package Models;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import Models.SpaceMarine;
import Models.WeaponType;

import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Класс, управляющий коллекцией объектов класса SpaceMarine
 */
public class CollectionManager implements Serializable {


    //region Поля
    /**
     * Дата инициализации
     */
    private LocalDate initializationDate;
    /**
     * Коллекция объектов
     */
    private LinkedList<SpaceMarine> marines;

    /**
     * Список выполненных команд
     */
    private LinkedList<String> commandHistory;

    /**
     * Файл с данными
     */
    private File dataFile;

    /**
     * объект библиотеки
     * для преобразования объектов Java в формат JSON
     */
    private Gson gson;

    /**
     * Последний вставленный ID
     */
    private int lastId = 0;

    /**
     * Скрипты выполняемые в данный момента командами execute_script
     */
    private HashSet<String> executedScripts;
    //endregion

    //region Конструкторы
    public CollectionManager(String filename) throws Exception {
        executedScripts = new HashSet<>();
        marines = new LinkedList<>();
        dataFile = new File(filename);
        gson = new Gson();
        initializationDate = LocalDate.now();
        commandHistory = new LinkedList<>();
        loadFromFile();
    }
    //endregion

    //region Методы

    //region Исполняемые скриптыы
    public void AddExecuteScript(String fileName) {
        this.executedScripts.add(fileName);
    }

    public void RemoveExecuteScript(String fileName) {
        this.executedScripts.remove(fileName);
    }

    public boolean CheckExecuteScript(String fileName) {
        return this.executedScripts.contains(fileName);
    }
    //endregion

    /**
     * Возвращает коллекцию объектов менеджера
     *
     * @return
     */
    public LinkedList<SpaceMarine> getMarines() {
        return this.marines;
    }

    /**
     * Возвращает дату инициализации
     *
     * @return
     */
    public LocalDate getInitializationDate() {
        return initializationDate;
    }


    /**
     * Загрузка из файла
     */
    private void loadFromFile() throws Exception {
        try (Reader reader = new InputStreamReader(new FileInputStream(dataFile))) {
            marines = gson.fromJson(reader, new TypeToken<LinkedList<SpaceMarine>>() {
            }.getType());

            //region Генерация ID и проверка на дубликаты
            for (SpaceMarine marine : marines) {
                if (marine.getId() <= lastId) {
                    marine.setId(++lastId);
                } else {
                    lastId = marine.getId();
                }
            }
            //endregion

            //region Проверка полей на правильность заполнения
            LinkedList<SpaceMarine> marinesTemp = new LinkedList<SpaceMarine>();
            for (SpaceMarine marine : marines) {
                try {
                    marinesTemp.add(new SpaceMarine(marine));
                } catch (Exception ex) {
                    System.out.println(String.format("Элемент не был загружен, его описание:'%s'", marine));
                }
            }
            //endregion

        } catch (IOException e) {
            System.err.println("Не удалось загрузить файл: " + e.getMessage());
        }
    }

    /**
     * Сохранение в файл
     */
    public boolean saveToFile() {
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(dataFile))) {
            gson.toJson(marines, writer);
            return true;
        } catch (IOException e) {
            System.err.println("Failed to save collection to file: " + e.getMessage());
            return false;
        }
    }


    /**
     * /**
     * Добавляет элемент в коллекцию, делает сортировку
     *
     * @param marine
     * @return последний вставленный ID
     * @throws Exception
     */
    public int add(SpaceMarine marine) throws Exception {
        if (this.marines == null)
            this.marines = new LinkedList<>();
        if (marine.getId() <= lastId) {
            marine.setId(++lastId);
        } else {
            lastId = marine.getId();
        }
        marines.add(marine);
        Collections.sort(marines);
        return lastId;
    }


    /**
     * Обновляет значение элемента коллекции, id которого равен заданному, делает сортировку
     *
     * @param id     ИД обновлемого объекта
     * @param marine новый объект для замены
     * @return True - если запись была найдена и обновлена
     */
    public boolean update(int id, SpaceMarine marine) throws Exception {
        for (int i = 0; i < marines.size(); i++) {
            if (marines.get(i).getId() == id) {
                marine.setId(id);
                marines.set(i, marine);
                return true;
            }
        }
        return false;
    }

    /**
     * Удаляет элемент из коллекции по ИД
     *
     * @param id ИД удаляемого объекта
     */
    public boolean removeById(int id) {
        return marines.removeIf(marine -> marine.getId() == id);
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        marines.clear();
    }

    /**
     * Выводит первый элемент коллекции и удаляет его
     */
    public SpaceMarine removeHead() {
        return marines.pollFirst();
    }

    /**
     * Добавить новый элемент в коллекцию,
     * если его значение меньше, чем у наименьшего элемента этой коллекции
     *
     * @param marine Объект для добавления
     */
    public boolean addIfMin(SpaceMarine marine) {
        SpaceMarine minMarine = Collections.min(marines);
        if (marine.compareTo(minMarine) < 0) {
            marines.addFirst(marine);
            return true;
        }
        return false;
    }


    /**
     * Добавляет указанную команду в
     * список последних выполненных команд
     *
     * @param command команда для добавления
     */
    public void addToHistory(String command) {
        if (commandHistory.size() == 5) {
            commandHistory.removeFirst();
        }
        commandHistory.addLast(command);
    }


    /**
     * Возвращает список выполненных команд
     *
     * @return
     */
    public LinkedList<String> history() {
        return commandHistory;
    }

    /**
     * Возвращает количество объектов у которых значение здоровья меньше указанного
     *
     * @param health значение здоровья
     * @return
     */
    public long countLessThanHealth(int health) {
        return marines.stream().filter(marine -> marine.getHealth() < health).count();
    }


    /**
     * Вывести элементы, значение поля name которых содержит заданную подстроку
     *
     * @param name подстрока для поиска
     * @return
     */
    public List<SpaceMarine> filterContainsName(String name) {
        return marines.stream().filter(marine -> marine.getName().contains(name)).collect(Collectors.toList());
    }

    /**
     * Вывести значения поля weaponType всех элементов в порядке убывания
     *
     * @return
     */
    public List<WeaponType> printFieldDescendingWeaponType() {
        return marines.stream()
                .map(SpaceMarine::getWeaponType)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }


    /**
     * Возвращает информацию о коллекции в виде строки.
     *
     * @return информация о коллекции
     */
    public String info() {
        String collectionType = marines.getClass().getName();
        String initializationDate = String.valueOf(this.getInitializationDate());
        int numberOfElements = marines.size();

        return "Type of collection: " + collectionType + "\n" +
                "Initialization date: " + initializationDate + "\n" +
                "Number of elements: " + numberOfElements;
    }

    /**
     * Возвращает элементы коллекции в виде строки.
     *
     * @return элементы коллекции
     */
    public String show() {
        if (marines.size() == 0)
            return "Коллекция пуста!";

        StringBuilder sb = new StringBuilder();
        for (SpaceMarine marine : marines) {
            sb.append(marine.toString()).append("\n");
        }
        return sb.toString();
    }


    /**
     * Класс, возвращающий коллеклицю Spacemarine'ов
     *
     * @return
     */
    public LinkedList<SpaceMarine> getCollection() {
        return marines;
    }


    /**
     * Этот метод будет возвращать первый объект SpaceMarine,
     * у которого id совпадает с заданным, или null,
     * если такого объекта в коллекции нет.
     *
     * @param id ID объекта
     * @return
     */
    public SpaceMarine getMarineById(int id) {
        return marines.stream()
                .filter(marine -> marine.getId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Возвращает последний добавленный ID объекта
     *
     * @return последний добавленный ID объекта
     */
    public int getLastid() {
        return this.lastId;
    }

    //endregion
}
