package factories.chess;

import chess.board.BoardArea;
import chess.figures.Queen;
import factories.chess.Abstract.AbstractBoardFactory;
import factories.chess.Interfaces.IBoardFactory;
import game.core.area.src.Interfaces.IArea;

import java.awt.*;

public class BoardFactory extends AbstractBoardFactory implements IBoardFactory {
    /**
     * @return Возвращает тестовую доску
     */
    public IArea getTestBoard() {
        IArea desk = new BoardArea(8);

        desk.setObject(new Queen(34, null));
        desk.setObject(new Queen(25, Color.WHITE));
        return desk;
    }
}
