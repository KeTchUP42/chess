package objects.board.factory;

import area.src.Interfaces.IArea;
import objects.board.BoardArea;
import objects.board.factory.Abstract.AbstractBoardFactory;
import objects.board.factory.Interfaces.IBoardFactory;
import objects.figures.Queen;
import objects.figures.src.colors.GameColors;

public class BoardFactory extends AbstractBoardFactory implements IBoardFactory {
    /**
     * @return Возвращает тестовую доску
     */
    public IArea getTestBoard() {
        IArea desk = new BoardArea(8);
        desk.setObject(new Queen(34, GameColors.secondColor));
        desk.setObject(new Queen(25, GameColors.firstColor));
        return desk;
    }
}
