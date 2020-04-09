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
     * Метод реализует движение объекта на области, предварительно проверяя возможность хода
     * Также дозаписывается история ходов для откатов
     *
     * @param SquareNumber Куда объект хочет ударить
     * @param area         Доска где стоит объект
     * @return Возвращает возможно ли это
     */
    public boolean move(int SquareNumber, IArea area, boolean rangeCheck) {
        boolean isInRange = !rangeCheck || this.isInRange(SquareNumber, area);
        //Проверяем нет ли нового объекта под нами
        if (isInRange && area.getObjectFromList(this.squareNumber) != null &&
                area.getObjectFromList(this.squareNumber).getObjectId() != this.objectId) {
            //Правильно заполняем очередь клонов
            area.setLastDestroyedObject(SquareNumber);
            area.setLastMovedObject(this);
            //Меняемся id так как нашего объекта на области нет, топерь новый объект это мы
            area.getObjectFromList(this.squareNumber).setObjectIdUnsafe(this.getObjectId());
            //Двигаем новый объект на нужную клетку без проверки на валидность хода с его стороны
            area.getObjectFromList(this.squareNumber).move(SquareNumber, area, false);
            //Очищаем историю хода
            area.lastDestroyedObjectDelete();
            area.lastMovedObjectDelete();
            return true;
        }
        if (isInRange) {
            area.setLastMovedObject(this.getSquareNumber());
            this.lastPosition = this.getSquareNumber();
            this.squareNumber = SquareNumber;
            area.setLastDestroyedObject(SquareNumber);
            area.setObject(this);
            area.deleteObject(this.lastPosition);
        }
        return isInRange;
    }

    /**
     * Метод может реализовывать какое либо действие, работает в связке с this.isInRange
     * Реализация предполагается в классах потомках
     *
     * @param SquareNumber Номер клетки над которой производится действие
     * @param Area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    public boolean action(int SquareNumber, IArea Area) {
        return false;
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

        for (int index = this.getSquareNumber() + sizeMn * area.getAreaWidth() + numMn;
             index != SquareNumber;
             index += sizeMn * area.getAreaWidth() + numMn
        ) {
            if (area.getObjectFromList(index) != null) {
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

        for (int index = numAlg ?
                this.getSquareNumber() + numMn * area.getAreaWidth() :
                this.getSquareNumber() + numMn;
             index != SquareNumber;
             index += numAlg ? numMn * area.getAreaWidth() : numMn
        ) {
            if (area.getObjectFromList(index) != null) {
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
