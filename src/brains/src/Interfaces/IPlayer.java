package brains.src.Interfaces;

import java.awt.*;

/**
 * @author Roman
 */
public interface IPlayer {
    /**
     * Ход данного игрока
     *
     * @return 0 - норма, 1 - проиграл, 2 - ход невозможен
     */
    int step();

    /**
     * Метод возвращает цвет
     *
     * @return Тип Color
     */
    Color getColor();
}
