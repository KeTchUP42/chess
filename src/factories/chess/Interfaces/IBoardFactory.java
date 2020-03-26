package factories.chess.Interfaces;

import game.core.area.src.Interfaces.IArea;

public interface IBoardFactory {
    /**
     * @return Возвращает готовую доску
     */
    IArea getStandardBoard();
}
