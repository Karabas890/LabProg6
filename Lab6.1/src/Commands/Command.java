package Commands;

import Models.CollectionManager;

import java.io.Serializable;

/**
 * Базовый класс команды
 */
public abstract class Command implements Serializable {

    //region Свойства для команд: имена и описания
    public static class Titles {
        public static String help = "help";
        public static String info = "info";
        public static String show = "show";
        public static String add = "add";
        public static String update = "update";
        public static String removeById = "remove_by_id";
        public static String clear = "clear";
        public static String save = "save";
        public static String executeScript = "execute_script";
        public static String exit = "exit";
        public static String removeHead = "remove_head";
        public static String addIfMin = "add_if_min";
        public static String history = "history";
        public static String countLessThanHealth = "count_less_than_health";
        public static String filterContainsName = "filter_contains_name";
        public static String printFieldDescendingWeaponType = "print_field_descending_weapon_type";
    }

    public static class Descriptions {
        public static String help = "вывести справку по доступным командам";
        public static String info = "вывести в стандартный поток вывода информацию о коллекции";
        public static String show = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
        public static String add = "добавить новый элемент в коллекцию";
        public static String update = "обновить объект с указанным ID";
        public static String removeById = "удалить объект с указанным ID";
        public static String clear = "очистить коллекцию";
        public static String save = "сохранить коллекцию в файл";
        public static String executeScript = "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
        public static String exit = "завершить программу (без сохранения в файл)";
        public static String removeHead = "вывести первый элемент коллекции и удалить его";
        public static String addIfMin = "добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
        public static String history = "вывести последние 5 команд (без их аргументов)";
        public static String countLessThanHealth = "вывести количество элементов, значение поля health которых меньше указанного";
        public static String filterContainsName = "вывести элементы, значение поля name которых содержит заданную подстроку";
        public static String printFieldDescendingWeaponType = "вывести значения поля weaponType всех элементов в порядке убывания";
    }
    //endregion

    //region Поля
    /**
     * Название команды
     */
    private String name;
    /**
     * Описание
     */
    private String description;
    /**
     * Объект для управления коллекцией объектов
     */
    protected transient CollectionManager collectionManager;
    //endregion

    //region Конструкторы
    public Command(CollectionManager collectionManager) {
        this.name = null;
        this.description = null;
        this.collectionManager = collectionManager;
    }

    public Command(String name, CollectionManager collectionManager) {
        this.name = name;
        this.collectionManager = collectionManager;
    }

    public Command(String name, String description, CollectionManager collectionManager) {
        this.name = name;
        this.description = description;
        this.collectionManager = collectionManager;
    }
    //endregion

    //region Методы


    @Override
    public String toString() {
        return String.format("%s - %s", this.name, this.description);
    }

    /**
     * Проверяет входные параметры на корректность
     *
     * @param params              входные параметры
     * @param expectedParamsCount ожидаемое число входных параметров
     * @return
     * @throws Exception
     */
    public boolean CheckParams(Object[] params, int expectedParamsCount) throws Exception {
        if (expectedParamsCount < 0)
            throw new Exception("Ожидаемое число параметров у команды не может быть меньше нуля!");
        return params != null && params.length == expectedParamsCount;
    }

    /**
     * Возвращает имя команды
     *
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Устанавливает имя команды
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Выполняет команду с хранилищем объектов
     * и заносит запись в лог
     *
     * @param params параметры команды
     * @return результат выполнения команды, true - если элемент добавлен
     */
    public Object Execute(Object[] params) throws Exception {
        if (this.collectionManager != null) {
            if (this.name != null && !this.name.isEmpty())
                this.collectionManager.addToHistory(this.name);
            else
                this.collectionManager.addToHistory(this.getClass().getName());
        }
        return this.excecute(params);
    }

    /**
     * Выполняет команду с указанным параметром.
     *
     * @param param параметр команды
     * @return результат выполнения команды
     * @throws Exception если произошла ошибка при выполнении команды
     */
    public Object Execute(Object param) throws Exception {
        return this.Execute(new Object[]{param});
    }

    /**
     * Выполняет команду без параметров
     *
     * @return результат выполнения команды
     * @throws Exception если произошла ошибка при выполнении команды
     */
    public Object Execute() throws Exception {
        return this.Execute(null);
    }


    /**
     * Выполняет команду с хранилищем объектов
     *
     * @param params параметры команды
     * @return результат выполнения команды
     */
    protected abstract Object excecute(Object[] params) throws Exception;
    //endregion

}
