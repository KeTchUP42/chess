package objects.Abstract;

import area.Interfaces.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
abstract class AbstractObjectCore {

    /**
     * Стартовый id объекта
     */
    public static final long DEFAULT_START_OBJECT_ID = -1;

    /**
     * Последняя позиция объекта
     */
    protected int lastPosition;

    /**
     * Номер клетки объекта
     */
    protected int squareNumber;

    /**
     * Цвет объекта
     */
    protected Color color;

    /**
     * Уникальный номер объекта, задается в setObject
     * Используется для роллбэков
     */
    protected long objectId = DEFAULT_START_OBJECT_ID;

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractObjectCore(int squareNumber, Color color) {
        this.squareNumber = squareNumber;
        this.color = color;
    }

    /**
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    public boolean isInRange(int SquareNumber, @NotNull IArea area) {
        return area.getObjectFromList(SquareNumber) == null;
    }

    /**
     * Метод возвращает уникальный id объекта на области
     *
     * @return startPosition
     */
    public long getObjectId() {
        return objectId;
    }

    /**
     * Метод задает уникальный id на конкретной облсти
     *
     * @param area objectId
     */
    public void setObjectIdSafe(@NotNull IArea area) {
        if (this.objectId == DEFAULT_START_OBJECT_ID) this.objectId = area.getObjectId();
    }

    /**
     * Метод задает уникальный id на конкретной облсти
     *
     * @param objectId IObject
     */
    public void setObjectIdUnsafe(long objectId) {
        this.objectId = objectId;
    }

    /**
     * Reset индентефикатора объекта
     */
    public void resetObjectId() {
        this.objectId = DEFAULT_START_OBJECT_ID;
    }

    /**
     * @return SquareNumber
     */
    public int getSquareNumber() {
        return this.squareNumber;
    }

    /**
     * @return SquareNumber
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * @return SquareNumber
     */
    public int getLastPosition() {
        return this.lastPosition;
    }
}
