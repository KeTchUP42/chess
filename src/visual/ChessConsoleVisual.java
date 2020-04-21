package visual;

import area.IArea;
import area.factory.src.GameColors;
import objects.IObject;
import objects.figures.*;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import visual.standard.ConsoleVisual;

/**
 * @author Roman
 */
public class ChessConsoleVisual extends ConsoleVisual {

    /**
     * Цвет вывода для первого цвета
     */
    private static final String ANSI_RED = "\u001B[31m";

    /**
     * Цвет вывода для второго цвета
     */
    private static final String ANSI_BLUE = "\u001B[34m";

    /**
     * RESET на стандартный цвет консоли
     */
    private static final String ANSI_RESET = "\u001B[0m";

    /**
     * @param area Область для отображения
     */
    @Override
    public void Draw(@NotNull IArea area) {
        this.clearConsole();
        for (int i_Y = area.getAreaHeight() - 1; i_Y >= 0; i_Y--) {
            System.out.print(ANSI_RESET + "|");
            for (int i_X = 0; i_X < area.getAreaWidth(); i_X++) {
                int SquareNumber = area.getSquareNumber(i_X, i_Y);
                System.out.print(this.chooseVisual(area.getObjectFromList(SquareNumber)));
                System.out.print(ANSI_RESET + "|");
            }
            System.out.println();
        }
    }

    /**
     * Выбираем символ для каждой фигуры
     *
     * @param object Фигура
     * @return Символ
     */
    @NotNull
    @Contract(pure = true)
    protected String chooseVisual(IObject object) {
        if (object == null) return ANSI_RESET + " ";
        String colorCode = object.getColor() == GameColors.firstStepColor ?
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
