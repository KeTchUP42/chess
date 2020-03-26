package area.src.Abstract;

import objects.src.Interfaces.IObject;

import java.awt.*;

/**
 * @author Roman
 */
public abstract class AbstractArea {
    protected int maxSquareNumber;

    protected int AreaSize;

    protected IObject[] ObjectList;

    /**
     * @param areaSize Размер области
     */
    public AbstractArea(int areaSize) {
        this.AreaSize = areaSize;
        this.maxSquareNumber = this.AreaSize * this.AreaSize;
        this.ObjectList = new IObject[this.maxSquareNumber];
    }

    /**
     * @param object Объект
     * @return Возвращает удачно ли прошла установка
     */
    public abstract boolean setObject(IObject object);


    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    public abstract boolean moveObject(int ObjectSquareNumber, int SquareNumber);

    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    public abstract boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor);

    /**
     * Проверка может ли существовать такая клетка
     *
     * @param SquareNumber Номер клетки
     * @return Возвращает валиден ли номер клетки
     */
    protected boolean isValidNumber(int SquareNumber) {
        return SquareNumber >= 0 && SquareNumber < this.maxSquareNumber;
    }

    /**
     * @param ObjectSquareNumber Номер клетки на которой нужно удалить объект
     * @return Возвращает удачно ли прошло удаление
     */
    public boolean deleteObject(int ObjectSquareNumber) {
        if (this.isValidNumber(ObjectSquareNumber)) {
            this.ObjectList[ObjectSquareNumber] = null;
        }
        return this.isValidNumber(ObjectSquareNumber);
    }

    /**
     * @return Возвращает размер области
     */
    public int getAreaSize() {
        return this.AreaSize;
    }

    /**
     * @return Возвращает макс кол-во клеток
     */
    public int getMaxSquareNumber() {
        return this.maxSquareNumber;
    }

    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @return Возвращает нужный объект
     */
    public IObject getObjectFromList(int ObjectSquareNumber) {
        return this.isValidNumber(ObjectSquareNumber) ? this.ObjectList[ObjectSquareNumber] : null;
    }

    /**
     * Возвращает координату X
     *
     * @param SquareNumber Номер клетки
     * @return X
     */
    public int getXCoordinate(int SquareNumber) {
        return SquareNumber % this.getAreaSize();
    }

    /**
     * Возвращает координату Y
     *
     * @param SquareNumber Номер клетки
     * @return Y
     */
    public int getYCoordinate(int SquareNumber) {
        return SquareNumber / this.getAreaSize();
    }

    /**
     * Конвертирует координаты в номер клетки
     *
     * @param XCoordinate X
     * @param YCoordinate Y
     * @return Номер клетки
     */
    public int getSquareNumber(int XCoordinate, int YCoordinate) {
        return YCoordinate * this.getAreaSize() + XCoordinate;
    }
}
