package objects.src.Abstract;

import area.src.Interfaces.IArea;
import objects.src.Interfaces.IObject;
import org.jetbrains.annotations.NotNull;

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

    /**
     * Проверка свободен ли путь для хода по диагонали
     *
     * @param SquareNumber Таргет номер клетки
     * @param area         Область
     * @return Возвращает возможно ли это
     */
    protected boolean isWayFreeDiagonal(int SquareNumber, @NotNull IArea area) {
        //Проверяем возможен ли ход
        if (!(Math.abs(area.getYCoordinate(this.getSquareNumber()) - area.getYCoordinate(SquareNumber)) ==
                Math.abs(area.getXCoordinate(this.getSquareNumber()) - area.getXCoordinate(SquareNumber))))
            return false;
        //Получаем координаты фигуры
        int figureX = area.getXCoordinate(this.getSquareNumber());
        int figureY = area.getYCoordinate(this.getSquareNumber());
        //Получаем координаты цели
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Получаем множетели для просчета
        int numMn = targetX > figureX ? 1 : -1;
        int sizeMn = targetY > figureY ? 1 : -1;

        boolean wayIsFree = true;

        for (int i = this.getSquareNumber() + sizeMn * area.getAreaSize() + numMn;
             i != SquareNumber;
             i += sizeMn * area.getAreaSize() + numMn
        ) {
            if (area.getObjectFromList(i) != null) {
                wayIsFree = false;
                break;
            }
        }
        return wayIsFree;
    }

    /**
     * Проверка валидности хода по горизонтали
     *
     * @param SquareNumber Таргет номер клетки
     * @param area         Область
     * @return Возвращает возможно ли это
     */
    protected boolean isWayFreePerpendicular(int SquareNumber, @NotNull IArea area) {
        //Проверяем возможен ли ход
        if (!(area.getYCoordinate(this.getSquareNumber()) == area.getYCoordinate(SquareNumber)
                || area.getXCoordinate(this.getSquareNumber()) == area.getXCoordinate(SquareNumber))) return false;
        //Получаем координаты фигуры
        int figureX = area.getXCoordinate(this.getSquareNumber());
        int figureY = area.getYCoordinate(this.getSquareNumber());
        //Получаем координаты цели
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Получаем множетели для просчета
        int numMn = 0;
        boolean numAlg = figureX == targetX;
        if (figureX == targetX)
            numMn = targetY > figureY ? 1 : -1;
        else if (figureY == targetY)
            numMn = targetX > figureX ? 1 : -1;

        boolean wayIsFree = true;

        for (int i = numAlg ?
                this.getSquareNumber() + numMn * area.getAreaSize() :
                this.getSquareNumber() + numMn;
             i != SquareNumber;
             i += numAlg ? numMn * area.getAreaSize() : numMn
        ) {
            if (area.getObjectFromList(i) != null) {
                wayIsFree = false;
                break;
            }
        }
        return wayIsFree;

    }

}
