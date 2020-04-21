package objects;

import area.IArea;
import area.snapshot.SnapShot;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractObject extends AbstractObjectBase implements Cloneable, IObject {

    /**
     * @param squareNumber Номер клетки
     * @param color        Цвет
     */
    public AbstractObject(int squareNumber, Color color) {
        super(squareNumber, color);
    }

    /**
     * Метод реализует движение объекта на области, предварительно проверяя возможность хода
     * Также до записывается история ходов для откатов
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
            area.setSnapShot(new SnapShot().
                    setDestroyedObject(area.getObjectFromList(SquareNumber)).
                    setMovedObject(this));
            //Меняемся id так как нашего объекта на области нет, теперь новый объект это мы
            area.getObjectFromList(this.squareNumber).setObjectIdUnsafe(this.getObjectId());
            //Двигаем новый объект на нужную клетку без проверки на валидность хода с его стороны
            area.getObjectFromList(this.squareNumber).move(SquareNumber, area, false);
            //Очищаем историю хода
            area.removeLastSnapShot();
            return true;
        }
        if (isInRange) {
            SnapShot snapShot = new SnapShot();
            snapShot.setMovedObject(area.getObjectFromList(this.getSquareNumber()));
            this.lastPosition = this.getSquareNumber();
            this.squareNumber = SquareNumber;
            snapShot.setDestroyedObject(area.getObjectFromList(SquareNumber));
            area.putObject(this);
            area.deleteObject(this.lastPosition);
            area.setSnapShot(snapShot);
        }
        return isInRange;
    }

    /**
     * Метод может реализовывать какое либо действие в связке с this.isInRange
     * Реализация предполагается в классах потомках
     *
     * @param SquareNumber Номер клетки над которой производится действие
     * @param area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    public boolean action(int SquareNumber, IArea area) {
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
        //Получаем координаты объекта
        int objectX = area.getXCoordinate(this.getSquareNumber());
        int objectY = area.getYCoordinate(this.getSquareNumber());
        //Получаем координаты цели
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Получаем множители для просчета
        int multiplier = targetX > objectX ? 1 : -1;
        int sizeMn = targetY > objectY ? 1 : -1;

        boolean wayIsFree = true;

        for (int index = this.getSquareNumber() + sizeMn * area.getAreaWidth() + multiplier;
             index != SquareNumber;
             index += sizeMn * area.getAreaWidth() + multiplier
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
        //Получаем координаты объекта
        int objectX = area.getXCoordinate(this.getSquareNumber());
        int objectY = area.getYCoordinate(this.getSquareNumber());
        //Получаем координаты цели
        int targetX = area.getXCoordinate(SquareNumber);
        int targetY = area.getYCoordinate(SquareNumber);
        //Получаем множители для просчета
        int multiplier = 0;
        boolean numAlg = objectX == targetX;
        if (objectX == targetX)
            multiplier = targetY > objectY ? 1 : -1;
        else if (objectY == targetY)
            multiplier = targetX > objectX ? 1 : -1;

        boolean wayIsFree = true;

        for (int index = numAlg ?
                this.getSquareNumber() + multiplier * area.getAreaWidth() :
                this.getSquareNumber() + multiplier;
             index != SquareNumber;
             index += numAlg ? multiplier * area.getAreaWidth() : multiplier
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
