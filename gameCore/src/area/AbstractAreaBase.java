package area;

import objects.IObject;

/**
 * @author Roman
 */
abstract class AbstractAreaBase {

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
    public AbstractAreaBase(int areaWidth, int areaHeight) {
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
    public boolean isValidSquareNumber(int SquareNumber) {
        return SquareNumber >= 0 && SquareNumber < this.maxSquareNumber;
    }

    /**
     * @return getObjectId
     */
    public long getObjectId() {
        return ++this.objectId;
    }

    /**
     * @return Возвращает ширину области
     */
    public int getAreaWidth() {
        return this.areaWidth;
    }

    /**
     * @return Возвращает высоту области
     */
    public int getAreaHeight() {
        return this.areaHeight;
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
        return this.isValidSquareNumber(ObjectSquareNumber) ? this.ObjectList[ObjectSquareNumber] : null;
    }

    /**
     * Проверка на null не может быть заменённая на аннотацию так как метод должен работать с null значениями
     *
     * @param object Объект
     */
    public void putObject(IObject object) {
        if (object != null && this.isValidSquareNumber(object.getSquareNumber())) {
            this.ObjectList[object.getSquareNumber()] = object;
            object.setObjectIdSafe((IArea) this);
        }
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
        return SquareNumber / this.getAreaWidth();
    }

    /**
     * Конвертирует координаты в номер клетки
     *
     * @param XCoordinate X
     * @param YCoordinate Y
     * @return Номер клетки
     */
    public int getSquareNumber(int XCoordinate, int YCoordinate) {
        return YCoordinate * this.getAreaWidth() + XCoordinate;
    }

    /**
     * Небезопасное движение объекта по области
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    public boolean moveObject(int ObjectSquareNumber, int SquareNumber) {
        return this.isValidSquareNumber(ObjectSquareNumber) && this.isValidSquareNumber(SquareNumber)
                && this.ObjectList[ObjectSquareNumber] != null && ObjectSquareNumber != SquareNumber &&
                this.ObjectList[ObjectSquareNumber].move(SquareNumber, (IArea) this, true);
    }

    /**
     * Метод удаляет объект на заданной клетке
     *
     * @param ObjectSquareNumber Номер клетки на которой нужно "удалить" объект
     */
    public void deleteObject(int ObjectSquareNumber) {
        if (this.isValidSquareNumber(ObjectSquareNumber)) {
            this.ObjectList[ObjectSquareNumber] = null;
        }
    }
}
