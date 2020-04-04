package visual.standard;

import area.src.Interfaces.IArea;
import objects.figures.*;
import objects.src.Interfaces.IObject;
import objects.src.colors.GameColors;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import visual.src.Interfaces.IVisual;

import java.io.File;
import java.util.Scanner;

public class CommandLine implements IVisual {
    //Цвет вывода для первого цвета
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
            //Базовый функционал в консоли
            while (true) {
                dialogResult = in.nextLine().trim();
                if (this.consoleAction(dialogResult.toLowerCase())) {
                    System.out.println("\n" + message);
                    continue;
                }
                break;
            }
        }
        if (afterClear) this.clearScreen();
        return dialogResult;
    }

    /**
     * Метод реализующий реакцию консоли на определенные строки ввода
     *
     * @param input вводимая строка
     */
    private boolean consoleAction(String input) {

        if (input.equals("exit") || input.equals("die")) System.exit(0);
        if (input.equals("help")) {
            this.sendMessage("\nAreaTypes:\n-standard\n-test\nPlayer types:\n-bot_0\n-player\nColorConf:\n-standard - first\n-other - second", false, false);
            return true;
        }
        return false;
    }

    /**
     * Вопрос по поводу конфигов
     *
     * @return boolean
     */
    public String ConfigClarification() {
        if (!this.sendMessage("ЗАГРУЗИТЬ ПАРАМЕТРЫ ИЗ КОНФИГА?\nY/N", true, false)
                .toUpperCase()
                .equals("N")) {
            while (true) {
                String filePath = this.sendMessage("Введи путь к ini файлу конфига.", true, false);
                File file = new File(filePath);
                if (file.isFile() && file.exists() && file.canRead())
                    return filePath;
            }
        } else return null;
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
