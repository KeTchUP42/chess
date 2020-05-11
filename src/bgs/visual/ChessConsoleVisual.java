package bgs.visual;

import bgs.area.IArea;
import bgs.objects.IObject;
import bgs.objects.figures.*;
import bgs.visual.src.GameColors;
import bgs.visual.standard.ConsoleVisual;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public class ChessConsoleVisual extends ConsoleVisual {

    private static final String ANSI_RED = "\u001B[31m";

    private static final String ANSI_BLUE = "\u001B[34m";

    private static final String ANSI_RESET = "\u001B[0m";

    @Override
    public void Draw(@NotNull IArea area) {
        this.cleanConsole();
        for (int Y = area.getAreaHeight() - 1; Y >= 0; Y--) {
            System.out.print(ANSI_RESET + "|");
            for (int X = 0; X < area.getAreaWidth(); X++) {
                int SquareNumber = area.getSquareNumber(X, Y);
                System.out.print(this.chooseVisual(area.getObjectFromList(SquareNumber)));
                System.out.print(ANSI_RESET + "|");
            }
            System.out.println();
        }
    }

    /**
     * Method choose, how to draw figure
     *
     * @param
     * @return
     */
    @NotNull
    @Contract(pure = true)
    private String chooseVisual(IObject object) {
        if (object == null) return ANSI_RESET + " ";
        String colorCode = object.getColor() == GameColors.firstColor ?
                ANSI_BLUE : ANSI_RED;
        if (object instanceof Pawn) return colorCode + "P";
        if (object instanceof Knight) return colorCode + "H";
        if (object instanceof Bishop) return colorCode + "B";
        if (object instanceof Castle) return colorCode + "C";
        if (object instanceof Queen) return colorCode + "Q";
        if (object instanceof King) return colorCode + "K";
        return " ";
    }
}
