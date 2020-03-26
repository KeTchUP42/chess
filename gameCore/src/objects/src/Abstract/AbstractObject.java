package objects.src.Abstract;

import area.src.Interfaces.IArea;
import objects.src.Interfaces.IObject;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractObject {
    protected int squareNumber;
    protected Color color;

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractObject(int squareNumber, Color color) {
        this.squareNumber = squareNumber;
        this.color = color;
    }

    /**
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    public boolean isInRange(int SquareNumber, IArea area) {
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
     * @param SquareNumber Куда фигура хочет ударить
     * @param area         Доска где стоит фигура
     * @return Возвращает возможно ли это
     */
    public boolean move(int SquareNumber, IArea area) {
        boolean isInRange = this.isInRange(SquareNumber, area);
        if (isInRange) {
            int lastSquareNumber = this.squareNumber;
            this.squareNumber = SquareNumber;
            area.deleteObject(SquareNumber);
            area.setObject((IObject) this);
            area.deleteObject(lastSquareNumber);
        }
        return isInRange;
    }


}
