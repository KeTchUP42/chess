package factories.chess.Abstract;

import chess.board.BoardArea;
import chess.figures.*;
import game.core.area.src.Interfaces.IArea;

import java.awt.*;

public abstract class AbstractBoardFactory {
    /**
     * @return Возвращает готовую доску
     */
    public IArea getStandardBoard() {
        IArea Board = new BoardArea(8);

        Board.setObject(new Castle(0, Color.WHITE));
        Board.setObject(new Knight(1, Color.WHITE));
        Board.setObject(new Bishop(2, Color.WHITE));
        Board.setObject(new Queen(3, Color.WHITE));
        Board.setObject(new King(4, Color.WHITE));
        Board.setObject(new Bishop(5, Color.WHITE));
        Board.setObject(new Knight(6, Color.WHITE));
        Board.setObject(new Castle(7, Color.WHITE));
        this.fillPawns(Board);
        Board.setObject(new Castle(56, Color.BLACK));
        Board.setObject(new Knight(57, Color.BLACK));
        Board.setObject(new Bishop(58, Color.BLACK));
        Board.setObject(new Queen(59, Color.BLACK));
        Board.setObject(new King(60, Color.BLACK));
        Board.setObject(new Bishop(61, Color.BLACK));
        Board.setObject(new Knight(62, Color.BLACK));
        Board.setObject(new Castle(63, Color.BLACK));

        return Board;
    }

    /**
     * Метод заполняет две линии Pawns
     *
     * @param Board Объект доска
     */
    protected void fillPawns(IArea Board) {

        for (int iterator = Board.getAreaSize(); iterator < Board.getAreaSize() * 2; iterator++) {
            Board.setObject(new Pawn(iterator, Color.WHITE));
        }

        for (int iterator = Board.getMaxSquareNumber() - Board.getAreaSize() * 2;
             iterator < Board.getAreaSize() * (Board.getAreaSize() - 1);
             iterator++
        ) {
            Board.setObject(new Pawn(iterator, Color.BLACK));
        }
    }
}