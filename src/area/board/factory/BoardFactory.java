package area.board.factory;

import area.board.BoardArea;
import area.board.factory.src.Abstract.AbstractBoardFactory;
import area.board.factory.src.Interfaces.IBoardFactory;
import area.src.Interfaces.IArea;
import objects.figures.King;
import objects.figures.Queen;
import objects.src.colors.GameColors;

public class BoardFactory extends AbstractBoardFactory implements IBoardFactory {
    /**
     * @return Возвращает тестовую доску
     */
    public IArea getTestBoard() {
        IArea desk = new BoardArea(8);
        desk.setObject(new Queen(34, GameColors.secondColor));
        desk.setObject(new Queen(25, GameColors.firstColor));
        desk.setObject(new King(54, GameColors.secondColor));
        desk.setObject(new King(5, GameColors.firstColor));
        return desk;
    }
}
