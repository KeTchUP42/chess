package area.src.Interfaces;

import objects.src.Interfaces.IObject;

import java.awt.*;
import java.util.Iterator;

/**
 * @author Roman
 */
public interface IArea {
    /**
     * Удаление объекта с области
     *
     * @param ObjectSquareNumber Номер клетки на которой нужно передвинуть объект
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
     * @param ObjectSquareNumber Номер клетки с объектом
     * @param SquareNumber       Номер клетки куда нужно переместить объект
     * @param objectColor        Цвет объекта
     * @return Возвращает удачно ли прошло движение
     */
    boolean moveObjectSafe(int ObjectSquareNumber, int SquareNumber, Color objectColor);

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

    /**
     * Конвертирует координаты в номер клетки
     *
     * @param XCoordinate X
     * @param YCoordinate Y
     * @return Номер клетки
     */
    int getSquareNumber(int XCoordinate, int YCoordinate);

    /**
     * Получаем последний объект который двигался
     *
     * @return IObject
     */
    Iterator<IObject> getLastMovedObjectIterator();

    /**
     * последний объект который двигался
     *
     * @param SquareNumber int
     */
    boolean setLastMovedObject(int SquareNumber);

    /**
     * Получаем последний объект которого убили
     *
     * @return IObject
     */
    Iterator<IObject> getLastKilledObjectIterator();

    /**
     * последний объект которого убили
     *
     * @param SquareNumber int
     */
    boolean setLastKilledObject(int SquareNumber);


    /**
     * Восстанавливает последний ход
     */
    void recallLastStep(int loopTimes);
}
