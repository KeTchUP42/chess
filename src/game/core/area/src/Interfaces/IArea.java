package game.core.area.src.Interfaces;

import game.core.objects.src.Interfaces.IObject;

/**
 * @author Roman
 */
public interface IArea {
    /**
     * Удаление объекта с области
     *
     * @param ObjectSquareNumber Номер клетки на которой нужно удалить объект
     * @return Возвращает удачно ли прошло удаление
     */
    boolean deleteObject(int ObjectSquareNumber);

    /**
     * Помещаем объект на область если клетка пустая
     *
     * @param object Объект
     * @return Возвращает удачно ли прошла установка
     */
    boolean setObject(IObject object);

    /**
     * Движение объекта на области
     *
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @return Возвращает удачно ли прошло движение
     */
    boolean moveObject(int ObjectSquareNumber, int SquareNumber);

    /**
     * @return Возвращает размер области
     */
    int getAreaSize();

    /**
     * @return Возвращает макс кол-во клеток
     */
    int getMaxSquareNumber();

    /**
     * @param ObjectSquareNumber Номер клутки на которой находится объект
     * @return Возвращает нужный объект или null
     */
    IObject getObjectFromList(int ObjectSquareNumber);

    /**
     * Возвращает координату X
     *
     * @param SquareNumber Номер клетки
     * @return X
     */
    int getXCoordinate(int SquareNumber);

    /**
     * Возвращает координату Y
     *
     * @param SquareNumber Номер клетки
     * @return Y
     */
    int getYCoordinate(int SquareNumber);
}
