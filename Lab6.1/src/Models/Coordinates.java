package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Класс Coordinates содержит координаты x и y с определенными условиями.
 */
public class Coordinates implements Serializable {

    //region Поля
    /**
     * Координата X.
     */
    private float x;

    /**
     * Координата Y.
     */
    private int y;
    //endregion

    //region Конструкторы

    /**
     * Конструктор по умолчанию для класса Coordinates.
     */
    public Coordinates() {
    }

    /**
     * Конструктор для создания объекта Coordinates с заданными координатами.
     *
     * @param x координата X
     * @param y координата Y
     * @throws Exception если значения координат некорректны
     */
    public Coordinates(float x, int y) throws Exception {
        this.setX(x);
        this.setY(y);
    }

    /**
     * Создает новый объект Coordinates путем копирования значений из другого объекта Coordinates.
     *
     * @param coordinates объект Coordinates, который будет скопирован
     * @throws Exception если происходит ошибка в процессе копирования
     */
    public Coordinates(Coordinates coordinates) throws Exception {
        this.setX(coordinates.getX());
        this.setY(coordinates.getY());
    }
    //endregion


    //region Геттеры и сеттеры

    /**
     * Возвращает значение x координаты
     *
     * @return значение x
     */
    public float getX() {
        return x;
    }

    /**
     * Устанавливает значение x координаты
     *
     * @param x значение x
     * @return true, если значение успешно установлено, иначе false
     */
    public void setX(float x) throws Exception {
        if (x <= -298)
            throw new Exception("Значение X должно быть больше -298");
        this.x = x;
    }

    /**
     * Возвращает значение y координаты
     *
     * @return значение y
     */
    public int getY() {
        return y;
    }

    /**
     * Устанавливает значение y координаты
     *
     * @param y значение y
     * @return true, если значение успешно установлено, иначе false
     */
    public void setY(int y) throws Exception {
        if (y <= -126)
            throw new Exception(" Значение Y должно быть больше -126");
        this.y = y;
    }
    //endregion

    //region Переопределения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Float.compare(that.x, x) == 0 && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
    //endregion

}
