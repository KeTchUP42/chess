package visual.standard;

import area.src.Interfaces.IArea;
import objects.figures.*;
import objects.src.colors.GameColors;
import objects.src.Interfaces.IObject;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import visual.Interfaces.IVisual;

import java.util.Scanner;

public class CommandLine implements IVisual {
    //Цвет вывода для вервого цвета
    private static final String ANSI_RED = "\u001B[31m";
    //Цвет вывода для второго цвета
    private static final String ANSI_BLUE = "\u001B[34m";
    //Ресет на стандартный цвет консоли
    private static final String ANSI_RESET = "\u001B[0m";


    @Override
    public void Draw(@NotNull IArea area) {
        this.clearScreen();

        for (int i_Y = area.getAreaSize() - 1; i_Y >= 0; i_Y--) {
            System.out.print(CommandLine.ANSI_RESET + "|");
            for (int i_X = 0; i_X < area.getAreaSize(); i_X++) {
                int SquareNumber = area.getSquareNumber(i_X, i_Y);
                System.out.print(this.choseVisual(area.getObjectFromList(SquareNumber)));
                System.out.print(CommandLine.ANSI_RESET + "|");
            }
            System.out.println();
        }
    }

    /**
     * Выбираем символ для каждого вида фигур
     *
     * @param object Фигура
     * @return Символ
     */
    @NotNull
    @Contract(pure = true)
    private String choseVisual(IObject object) {
        if (object == null) return CommandLine.ANSI_RESET + " ";
        String colorCode = object.getColor() == GameColors.firstColor ?
                CommandLine.ANSI_BLUE :
                CommandLine.ANSI_RED;
        if (object instanceof Pawn) return colorCode + "P";
        if (object instanceof Knight) return colorCode + "H";
        if (object instanceof Bishop) return colorCode + "B";
        if (object instanceof Castle) return colorCode + "C";
        if (object instanceof Queen) return colorCode + "Q";
        if (object instanceof King) return colorCode + "K";
        return " ";
    }

    @Override
    public String sendMessage(String message, boolean getAnswer, boolean afterClear) {
        System.out.println(message);
        String dialogResult = null;
        if (getAnswer) {
            Scanner in = new Scanner(System.in);
            dialogResult = in.nextLine().trim();
        }
        if (afterClear) this.clearScreen();
        return dialogResult;
    }

    /**
     * Вопрос по поводу конфигов
     *
     * @return boolean
     */
    public boolean ConfigClarification() {
        return !this.sendMessage("ЗАГРУЗИТЬ ПАРАМЕТРЫ ИЗ КОНФИГА?\nY/N", true, false).toUpperCase().equals("N");
    }

    /**
     * Очистка консоли
     */
    private void clearScreen() {
        for (int clear = 0; clear < 25; clear++) {
            System.out.println("\b");
        }
    }

}
