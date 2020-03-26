package game.core.objects.src.Abstract;

import game.core.area.src.Interfaces.IArea;

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
        byte figureX = (byte) (area.getXCoordinate(this.getSquareNumber()));
        byte figureY = (byte) (area.getYCoordinate(this.getSquareNumber()));
        //Получаем координаты цели
        byte targetX = (byte) (area.getXCoordinate(SquareNumber));
        byte targetY = (byte) (area.getYCoordinate(SquareNumber));
        //Получаем множетели для просчета
        byte numMn = (byte) (targetX > figureX ? 1 : -1);
        byte sizeMn = (byte) (targetY > figureY ? 1 : -1);

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
        byte figureX = (byte) (area.getXCoordinate(this.getSquareNumber()));
        byte figureY = (byte) (area.getYCoordinate(this.getSquareNumber()));
        //Получаем координаты цели
        byte targetX = (byte) (area.getXCoordinate(SquareNumber));
        byte targetY = (byte) (area.getYCoordinate(SquareNumber));
        //Получаем множетели для просчета
        byte numMn = 0;
        boolean numAlg = figureX == targetX;
        if (figureX == targetX)
            numMn = (byte) (targetY > figureY ? 1 : -1);
        else if (figureY == targetY)
            numMn = (byte) (targetX > figureX ? 1 : -1);

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
