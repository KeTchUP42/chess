package objects.src.Abstract;

import area.src.Interfaces.IArea;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

abstract class AbstractObjectCore {
    protected int lastPosition;
    protected int squareNumber;
    protected Color color;

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
