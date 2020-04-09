package objects.Interfaces;

import area.Interfaces.IArea;

import java.awt.*;

/**
 * @author Roman
 */
public interface IObject {

    /**
     * Получаем номер клетки где должен стоять объект
     *
     * @return SquareNumber
     */
    int getSquareNumber();

    /**
     * @return Colors
     */
    Color getColor();

    /**
     * Получаем номер клетки последней позиции
     *
     * @return SquareNumber
     */
    int getLastPosition();

    /**
     * Получаем уникальный номер объекта
     *
     * @return SquareNumber
     */
    long getObjectId();

    /**
     * Метод задает уникальный id из данных области
     *
     * @param area objectId
     */
    void setObjectIdSafe(IArea area);

    /**
     * Метод задает уникальный id на конкретной облсти
     *
     * @param objectId IObject
     */
    void setObjectIdUnsafe(long objectId);

    /**
     * Reset индентефикатора объекта
     */
    void resetObjectId();

    /**
     * @param SquareNumber Куда объект хочет ударить/попасть
     * @param area         Область где стоит объект
     * @param rangeCheck   rangeCheck
     * @return Возвращает возможно ли это
     */
    boolean move(int SquareNumber, IArea area, boolean rangeCheck);

    /**
     * Метод может реализовывать какое либо действие, работает в связке с this.isInRange
     * Реализация предполагается в классах потомках
     *
     * @param SquareNumber Номер клетки над которой производится действие
     * @param Area         Область где стоит объект
     * @return Возвращает возможно ли это
     */
    boolean action(int SquareNumber, IArea Area);

    /**
     * Метод реализует проеврку возможности для хода каждого объект
     *
     * @param SquareNumber Куда объект хочет ударить
     * @param Board        Область где стоит объект
     * @return Возвращает возможно ли это
     */
    boolean isInRange(int SquareNumber, IArea Board);

    /**
     * Метод клонирования
     *
     * @return IObject
     * @throws CloneNotSupportedException Ошибка
     */
    IObject clone() throws CloneNotSupportedException;
}
