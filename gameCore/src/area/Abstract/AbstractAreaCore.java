package area.Abstract;

import area.Interfaces.IArea;
import objects.Interfaces.IObject;

abstract class AbstractAreaCore {

    protected int maxSquareNumber;

    protected int AreaSize;

    protected IObject[] ObjectList;

    /**
     * @param areaSize Размер области
     */
    public AbstractAreaCore(int areaSize) {
        this.AreaSize = areaSize;
        this.maxSquareNumber = this.AreaSize * this.AreaSize;
        this.ObjectList = new IObject[this.maxSquareNumber];
    }

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


    /**
     * Проверка на null не может быть замененна на аннотацию так как медот должен работать с null значениями
     *
     * @param object Объект
     */
    public void setObject(IObject object) {
        boolean isOk = object != null &&
                this.isValidNumber(object.getSquareNumber());
        if (isOk) {
            this.ObjectList[object.getSquareNumber()] = object;
        }
    }


    /**
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, (IArea) this);
    }


    /**
     * @param ObjectSquareNumber Номер клетки на которой нужно "удалить" объект
     */
    public void deleteObject(int ObjectSquareNumber) {
        if (this.isValidNumber(ObjectSquareNumber)) {
            this.ObjectList[ObjectSquareNumber] = null;
        }
    }


}
