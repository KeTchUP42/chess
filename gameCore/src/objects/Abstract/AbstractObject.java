package objects.Abstract;

import area.Interfaces.IArea;
import objects.Interfaces.IObject;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractObject extends AbstractObjectCore implements Cloneable, IObject {


    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractObject(int squareNumber, Color color) {
        super(squareNumber, color);
    }


    /**
     * Метод реализует движение фигуры на области, предварительно проверяя возможность хода
     * Также дозаписывается история ходов для откатов
     *
     * @param SquareNumber Куда фигура хочет ударить
     * @param area         Доска где стоит фигура
     * @return Возвращает возможно ли это
     */
    public boolean move(int SquareNumber, IArea area) {
        boolean isInRange = this.isInRange(SquareNumber, area);
        if (isInRange) {
            area.setLastMovedObject(this.getSquareNumber());
            this.lastPosition = this.getSquareNumber();
            this.squareNumber = SquareNumber;
            area.setLastKilledObject(SquareNumber);
            area.setObject(this);
            area.deleteObject(this.lastPosition);
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

    /**
     * Метод клонирования
     *
     * @return this
     * @throws CloneNotSupportedException Ошибка
     */
    public IObject clone() throws CloneNotSupportedException {
        return (IObject) super.clone();
    }
}
