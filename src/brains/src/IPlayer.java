package brains.src;

import brains.src.rep.StepLog;

import java.awt.*;

/**
 * @author Roman
 */
public interface IPlayer {

    /**
     * Ход данного игрока
     *
     * @return StepLog
     */
    StepLog step();

    /**
     * Метод возвращает цвет
     *
     * @return Тип Color
     */
    Color getColor();
}
