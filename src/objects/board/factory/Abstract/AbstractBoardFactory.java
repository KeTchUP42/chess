package objects.board.factory.Abstract;

import area.src.Interfaces.IArea;
import objects.board.BoardArea;
import objects.figures.*;
import objects.figures.src.colors.GameColors;

public abstract class AbstractBoardFactory {
    /**
     * @return Возвращает готовую доску
     */
    public IArea getStandardBoard() {
        IArea Board = new BoardArea(8);

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
    protected void fillPawns(IArea Board) {

        for (int iterator = Board.getAreaSize(); iterator < Board.getAreaSize() * 2; iterator++) {
            Board.setObject(new Pawn(iterator, GameColors.firstColor));
        }

        for (int iterator = Board.getMaxSquareNumber() - Board.getAreaSize() * 2;
             iterator < Board.getAreaSize() * (Board.getAreaSize() - 1);
             iterator++
        ) {
            Board.setObject(new Pawn(iterator, GameColors.secondColor));
        }
    }
}