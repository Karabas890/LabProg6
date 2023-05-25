package Models;

import java.io.Serializable;
import java.util.Objects;

/**
 * класс, представляющий космического морского пехотинца.
 * Он содержит информацию о различных атрибутах пехотинца, таких как идентификатор,
 * имя, координаты, дата создания, здоровье, достижения, категория, тип оружия и глава.
 */
public class SpaceMarine implements Comparable<SpaceMarine>, Serializable {

    //region Поля
    /**
     * Уникальный идентификатор SpaceMarine.
     */
    private int id;

    /**
     * Имя SpaceMarine.
     */
    private String name;

    /**
     * Координаты SpaceMarine.
     */
    private Coordinates coordinates;

    /**
     * Дата создания SpaceMarine.
     */
    private String creationDate;

    /**
     * Здоровье SpaceMarine.
     */
    private Integer health;

    /**
     * Достижения SpaceMarine.
     */
    private String achievements;

    /**
     * Категория Astartes SpaceMarine.
     */
    private AstartesCategory category;

    /**
     * Тип оружия SpaceMarine.
     */
    private WeaponType weaponType;

    /**
     * Глава Chapter, к которой принадлежит SpaceMarine.
     */
    private Chapter chapter;
    //endregion

    //region Конструкторы
    public SpaceMarine() {
    }

    /**
     * Конструктор для создания объекта SpaceMarine с заданными значениями атрибутов.
     *
     * @param id           идентификатор пехотинца
     * @param name         имя пехотинца
     * @param coordinates  координаты пехотинца
     * @param creationDate дата создания пехотинца
     * @param health       здоровье пехотинца
     * @param achievements достижения пехотинца
     * @param category     категория пехотинца
     * @param weaponType   тип оружия пехотинца
     * @param chapter      глава пехотинца
     * @throws Exception выбрасывает исключение, если значения атрибутов некорректны
     */
    public SpaceMarine(int id,
                       String name,
                       Coordinates coordinates,
                       String creationDate,
                       Integer health,
                       String achievements,
                       AstartesCategory category,
                       WeaponType weaponType,
                       Chapter chapter) throws Exception {
        this.setId(id);
        this.setName(name);
        this.setCoordinates(coordinates);
        this.setCreationDate(creationDate);
        this.setHealth(health);
        this.setAchievements(achievements);
        this.setCategory(category);
        this.setWeaponType(weaponType);
        this.setChapter(chapter);
    }

    /**
     * Создает новый объект SpaceMarine путем копирования значений из другого объекта SpaceMarine.
     *
     * @param spaceMarine объект SpaceMarine, который будет скопирован
     * @throws Exception если происходит ошибка в процессе копирования
     */
    public SpaceMarine(SpaceMarine spaceMarine) throws Exception {
        this.setId(spaceMarine.getId());
        this.setName(spaceMarine.getName());
        this.setCoordinates(new Coordinates(spaceMarine.getCoordinates()));
        this.setCreationDate(spaceMarine.getCreationDate());
        this.setHealth(spaceMarine.getHealth());
        this.setAchievements(spaceMarine.getAchievements());
        this.setCategory(spaceMarine.getCategory());
        this.setWeaponType(spaceMarine.getWeaponType());
        this.setChapter(new Chapter(spaceMarine.getChapter()));
    }

    //endregion

    //region Сеттеры и геттеры

    //region ID
    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        if (id < 0)
            throw new Exception("ID не может быть отрицательным");
        this.id = id;
    }
    //endregion

    //region Name
    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if (name == null || name.trim().isEmpty())
            throw new Exception("Имя не может быть пустым");
        this.name = name;
    }
    //endregion

    //region Coordinates
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
    //endregion

    //region CreationDate
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    //endregion

    //region Health
    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) throws Exception {
        if (health < 0)
            throw new Exception("Здоровье не может быть отрицательным");
        this.health = health;
    }
    //endregion

    //region Achievements
    public String getAchievements() {
        return achievements;
    }

    public void setAchievements(String achievements) throws Exception {
        this.achievements = achievements;
    }
    //endregion

    //region AstartesCategory
    public AstartesCategory getCategory() {
        return category;
    }

    public void setCategory(AstartesCategory category) {
        this.category = category;
    }
    //endregion

    //region WeaponType
    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }
    //endregion

    //region Chapter
    public Chapter getChapter() {
        return chapter;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }
    //endregion

    //endregion

    //region Переопределения
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpaceMarine that = (SpaceMarine) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(coordinates, that.coordinates)
                && Objects.equals(creationDate, that.creationDate) && Objects.equals(health, that.health) &&
                Objects.equals(achievements, that.achievements) && category == that.category
                && weaponType == that.weaponType && Objects.equals(chapter, that.chapter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, health, achievements, category, weaponType, chapter);
    }

    @Override
    public String toString() {
        return "SpaceMarine{" +
                "\n\tid=" + id +
                "\n\tname='" + name + '\'' +
                "\n\tcoordinates=" + coordinates +
                "\n\tcreationDate=" + creationDate +
                "\n\thealth=" + health +
                "\n\tachievements='" + achievements + '\'' +
                "\n\tcategory=" + category +
                "\n\tweaponType=" + weaponType +
                "\n\tchapter=" + chapter +
                "\n}";
    }


    @Override
    public int compareTo(SpaceMarine another) {
        if (this.getId() > another.getId()) {
            return 1;
        } else if (this.getId() == another.getId()) {
            return 0;
        } else {
            return -1;
        }
    }
    //endregion
}
