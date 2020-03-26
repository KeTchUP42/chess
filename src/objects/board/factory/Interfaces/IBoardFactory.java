package objects.board.factory.Interfaces;

import area.src.Interfaces.IArea;

public interface IBoardFactory {
    /**
     * @return Возвращает готовую доску
     */
    IArea getStandardBoard();
}
