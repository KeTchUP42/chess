package area.factory;

import area.BoardArea;
import area.IArea;
import area.factory.src.IAreaFactory;
import objects.figures.*;
import org.jetbrains.annotations.NotNull;
import tools.logger.Logger;
import visual.src.GameColors;

/**
 * @author Roman
 */
public class BoardFactory implements IAreaFactory {

    public IArea createStandardArea() {
        Logger.globalLogger.info("Board is 8x8"); //TODO
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
     * Method fills lines of pawns
     *
     * @param Board Board object
     */
    private void fillPawns(@NotNull IArea Board) {

        for (int iterator = Board.getAreaWidth(); iterator < Board.getAreaWidth() * 2; iterator++) {
            Board.putObject(new Pawn(iterator, GameColors.firstColor));
        }
        for (int iterator = Board.getMaxSquareNumber() - Board.getAreaWidth() * 2;
             iterator < Board.getAreaWidth() * (Board.getAreaWidth() - 1);
             iterator++
        ) {
            Board.putObject(new Pawn(iterator, GameColors.secondColor));
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
