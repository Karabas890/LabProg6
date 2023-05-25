package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс Chapter представляет собой главу, содержащую имя и родительский легион.
 */
public class Chapter implements Serializable {

    //region Поля
    private String name;            // Поле не может быть null, Строка не может быть пустой
    private String parentLegion;
    //endregion

    //region Конструкторы
    public Chapter() {
    }

    /**
     * Создает главу с указанными параметрами
     *
     * @param name
     * @param parentLegion
     * @throws Exception
     */
    public Chapter(String name, String parentLegion) throws Exception {
        this.setName(name);
        this.setParentLegion(parentLegion);
    }

    /**
     * Конструктор для создания объекта Chapter путем копирования значений из другого объекта Chapter.
     *
     * @param chapter объект Chapter, который будет скопирован
     * @throws Exception если происходит ошибка в процессе копирования
     */
    public Chapter(Chapter chapter) throws Exception {
        this.setName(chapter.getName());
        this.setParentLegion(chapter.getParentLegion());
    }

    //endregion

    //region Геттеры и сеттеры

    /**
     * Возвращает имя главы
     *
     * @return имя главы
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя главы
     *
     * @param name имя главы
     */
    public void setName(String name) throws Exception {

        if (name == null || name.trim().isEmpty())
            throw new Exception("Имя не может быть пустым");
        this.name = name;
    }

    /**
     * Возвращает имя родительского легиона
     *
     * @return имя родительского легиона
     */
    public String getParentLegion() {
        return parentLegion;
    }

    /**
     * Устанавливает имя родительского легиона
     *
     * @param parentLegion имя родительского легиона
     */
    public void setParentLegion(String parentLegion) {
        this.parentLegion = parentLegion;
    }
    //endregion

    //region Переопределения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(name, chapter.name) && Objects.equals(parentLegion, chapter.parentLegion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parentLegion);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "name='" + name + '\'' +
                ", parentLegion='" + parentLegion + '\'' +
                '}';
    }
    //endregion

}
