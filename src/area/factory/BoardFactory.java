package area.factory;

import area.Interfaces.IArea;
import area.board.BoardArea;
import area.factory.src.Interfaces.IAreaFactory;
import objects.figures.*;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public class BoardFactory implements IAreaFactory {

    /**
     * @return Возвращает заполненную доску
     */
    public IArea getStandardArea() {
        IArea Board = new BoardArea(8, 8);

        Board.setObject(new Castle(0, GameColors.firstColor));
        Board.setObject(new Knight(1, GameColors.firstColor));
        Board.setObject(new Bishop(2, GameColors.firstColor));
        Board.setObject(new Queen(3, GameColors.firstColor));
        Board.setObject(new King(4, GameColors.firstColor));
        Board.setObject(new Bishop(5, GameColors.firstColor));
        Board.setObject(new Knight(6, GameColors.firstColor));
        Board.setObject(new Castle(7, GameColors.firstColor));
        this.fillPawns(Board);
        Board.setObject(new Castle(56, GameColors.secondColor));
        Board.setObject(new Knight(57, GameColors.secondColor));
        Board.setObject(new Bishop(58, GameColors.secondColor));
        Board.setObject(new Queen(59, GameColors.secondColor));
        Board.setObject(new King(60, GameColors.secondColor));
        Board.setObject(new Bishop(61, GameColors.secondColor));
        Board.setObject(new Knight(62, GameColors.secondColor));
        Board.setObject(new Castle(63, GameColors.secondColor));

        return Board;
    }

    /**
     * Метод заполняет две линии Pawns
     *
     * @param Board Объект доска
     */
    protected void fillPawns(@NotNull IArea Board) {

        for (int iterator = Board.getAreaWidth(); iterator < Board.getAreaWidth() * 2; iterator++) {
            Board.setObject(new Pawn(iterator, GameColors.firstColor));
        }
        for (int iterator = Board.getMaxSquareNumber() - Board.getAreaWidth() * 2;
             iterator < Board.getAreaWidth() * (Board.getAreaWidth() - 1);
             iterator++
        ) {
            Board.setObject(new Pawn(iterator, GameColors.secondColor));
        }
    }

    /**
     * @return Возвращает тестовую доску
     */
    public IArea getTestArea() {
        IArea desk = new BoardArea(4, 4);
        desk.setObject(new King(15, GameColors.secondColor));
        desk.setObject(new Pawn(4, GameColors.firstColor));
        desk.setObject(new King(0, GameColors.firstColor));
        return desk;
    }
}
