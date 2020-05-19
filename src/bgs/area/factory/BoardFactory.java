package bgs.area.factory;

import bgs.area.BoardArea;
import bgs.area.IArea;
import bgs.area.factory.src.IAreaFactory;
import bgs.objects.figures.*;
import bgs.visual.src.GameColors;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public class BoardFactory implements IAreaFactory {

    public IArea createStandardArea() {
        IArea Board = new BoardArea(8, 8);

        Board.putObject(new Castle(0, GameColors.firstColor));
        Board.putObject(new Knight(1, GameColors.firstColor));
        Board.putObject(new Bishop(2, GameColors.firstColor));
        Board.putObject(new Queen(3, GameColors.firstColor));
        Board.putObject(new King(4, GameColors.firstColor));
        Board.putObject(new Bishop(5, GameColors.firstColor));
        Board.putObject(new Knight(6, GameColors.firstColor));
        Board.putObject(new Castle(7, GameColors.firstColor));
        this.fillPawns(Board);
        Board.putObject(new Castle(56, GameColors.secondColor));
        Board.putObject(new Knight(57, GameColors.secondColor));
        Board.putObject(new Bishop(58, GameColors.secondColor));
        Board.putObject(new Queen(59, GameColors.secondColor));
        Board.putObject(new King(60, GameColors.secondColor));
        Board.putObject(new Bishop(61, GameColors.secondColor));
        Board.putObject(new Knight(62, GameColors.secondColor));
        Board.putObject(new Castle(63, GameColors.secondColor));

        return Board;
    }

    /**
     * Method fill lines of pawns
     *
     * @param Board
     */
    private void fillPawns(@NotNull IArea Board) {

        for (int index = Board.getAreaWidth(); index < Board.getAreaWidth() * 2; index++) {
            Board.putObject(new Pawn(index, GameColors.firstColor));
        }
        for (int index = Board.getMaxSquareNumber() - Board.getAreaWidth() * 2;
             index < Board.getAreaWidth() * (Board.getAreaWidth() - 1);
             index++
        ) {
            Board.putObject(new Pawn(index, GameColors.secondColor));
        }
    }

    public IArea createTestArea() {
        IArea Board = new BoardArea(4, 4);
        Board.putObject(new King(15, GameColors.secondColor));
        Board.putObject(new Pawn(4, GameColors.firstColor));
        Board.putObject(new King(0, GameColors.firstColor));
        return Board;
    }
}
