package objects.Abstract;

import area.Interfaces.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

abstract class AbstractObjectCore {

    protected int lastPosition;

    protected int squareNumber;

    protected Color color;

    /**
     * Стартовая позиция объекта
     * Используется для роллбэков
     */
    protected int startPosition;

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractObjectCore(int squareNumber, Color color) {
        this.squareNumber = squareNumber;
        this.startPosition = squareNumber;
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
     * @return startPosition
     */
    public int getStartPosition() {
        return startPosition;
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
