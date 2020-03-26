package objects.src.Abstract;

import area.src.Interfaces.IArea;

import java.awt.*;


public abstract class AbstractRemoteAttacker extends AbstractObject {


    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractRemoteAttacker(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    /**
     * Проверка свободен ли путь для хода по диагонали
     *
     * @param SquareNumber Таргет номер клетки
     * @param area         Область
     * @return Возвращает возможно ли это
     */
    protected boolean isWayFreeDiagonal(int SquareNumber, IArea area) {
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
    protected boolean isWayFreePerpendicular(int SquareNumber, IArea area) {
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
