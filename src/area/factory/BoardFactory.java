package area.factory;

import area.BoardArea;
import area.IArea;
import area.factory.src.IAreaFactory;
import logger.Logger;
import objects.figures.*;
import org.jetbrains.annotations.NotNull;
import src.GameColors;

/**
 * @author Roman
 */
public class BoardFactory implements IAreaFactory {

    public IArea createStandardArea() {
        Logger.globalLogger.info("Board is 8x8"); //TODO
        IArea Board = new BoardArea(8, 8);

        Board.putObject(new Castle(0, GameColors.firstStepColor));
        Board.putObject(new Knight(1, GameColors.firstStepColor));
        Board.putObject(new Bishop(2, GameColors.firstStepColor));
        Board.putObject(new Queen(3, GameColors.firstStepColor));
        Board.putObject(new King(4, GameColors.firstStepColor));
        Board.putObject(new Bishop(5, GameColors.firstStepColor));
        Board.putObject(new Knight(6, GameColors.firstStepColor));
        Board.putObject(new Castle(7, GameColors.firstStepColor));
        this.fillPawns(Board);
        Board.putObject(new Castle(56, GameColors.secondStepColor));
        Board.putObject(new Knight(57, GameColors.secondStepColor));
        Board.putObject(new Bishop(58, GameColors.secondStepColor));
        Board.putObject(new Queen(59, GameColors.secondStepColor));
        Board.putObject(new King(60, GameColors.secondStepColor));
        Board.putObject(new Bishop(61, GameColors.secondStepColor));
        Board.putObject(new Knight(62, GameColors.secondStepColor));
        Board.putObject(new Castle(63, GameColors.secondStepColor));

        return Board;
    }

    /**
     * Method fills lines of pawns
     *
     * @param Board Board object
     */
    private void fillPawns(@NotNull IArea Board) {

        for (int iterator = Board.getAreaWidth(); iterator < Board.getAreaWidth() * 2; iterator++) {
            Board.putObject(new Pawn(iterator, GameColors.firstStepColor));
        }
        for (int iterator = Board.getMaxSquareNumber() - Board.getAreaWidth() * 2;
             iterator < Board.getAreaWidth() * (Board.getAreaWidth() - 1);
             iterator++
        ) {
            Board.putObject(new Pawn(iterator, GameColors.secondStepColor));
        }
    }

    public IArea createTestArea() {
        IArea desk = new BoardArea(4, 4);
        desk.putObject(new King(15, GameColors.secondStepColor));
        desk.putObject(new Pawn(4, GameColors.firstStepColor));
        desk.putObject(new King(0, GameColors.firstStepColor));
        return desk;
    }
}
