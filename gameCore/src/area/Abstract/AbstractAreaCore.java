package area.Abstract;

import area.Interfaces.IArea;
import objects.Interfaces.IObject;

/**
 * @author Roman
 */
abstract class AbstractAreaCore {

    /**
     * Максимальное кол-во клеток в области
     */
    protected int maxSquareNumber;

    /**
     * Ширина области
     */
    protected int areaWidth;

    /**
     * Высота области
     */
    protected int areaHeight;

    /**
     * Массив объектов области
     */
    protected IObject[] ObjectList;

    /**
     * Уникальный id задаваемого объекта
     */
    protected long objectId = 0;

    /**
     * @param areaWidth  Ширина области
     * @param areaHeight Высота области
     */
    public AbstractAreaCore(int areaWidth, int areaHeight) {
        this.areaWidth = areaWidth;
        this.areaHeight = areaHeight;
        this.maxSquareNumber = this.areaWidth * this.areaHeight;
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
     * @return getObjectId
     */
    public long getObjectId() {
        this.objectId++;
        return objectId;
    }

    /**
     * @return Возвращает ширину облатсти
     */
    public int getAreaWidth() {
        return areaWidth;
    }

    /**
     * @return Возвращает высоту области
     */
    public int getAreaHeight() {
        return areaHeight;
    }

    /**
     * @return Возвращает макс кол-во клеток
     */
    public int getMaxSquareNumber() {
        return this.maxSquareNumber;
    }

    /**
     * Метод возвращает ссылку на объект под номером клетки
     *
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
        return SquareNumber % this.getAreaWidth();
    }

    /**
     * Возвращает координату Y
     *
     * @param SquareNumber Номер клетки
     * @return Y
     */
    public int getYCoordinate(int SquareNumber) {
        return SquareNumber / getAreaWidth();
    }

    /**
     * Конвертирует координаты в номер клетки
     *
     * @param XCoordinate X
     * @param YCoordinate Y
     * @return Номер клетки
     */
    public int getSquareNumber(int XCoordinate, int YCoordinate) {
        return YCoordinate * getAreaWidth() + XCoordinate;
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
            object.setObjectIdSafe((IArea) this);
        }
    }

    /**
     * Небезопасное движение объекта по области
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidNumber(ObjectSquareNumber) && this.isValidNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, (IArea) this, true);
    }

    /**
     * Метод удаляет объект на заданной клетке
     *
     * @param ObjectSquareNumber Номер клетки на которой нужно "удалить" объект
     */
    public void deleteObject(int ObjectSquareNumber) {
        if (this.isValidNumber(ObjectSquareNumber)) {
            this.ObjectList[ObjectSquareNumber] = null;
        }
    }
}
