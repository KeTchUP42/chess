package visual.standard;

import area.src.Interfaces.IArea;
import visual.Interfaces.IVisual;

import java.util.Scanner;

public class CommandLine implements IVisual {
    /**
     * Возможно задать базовые параметры
     */
    public CommandLine() {
        // System.out.println("\u001b[32m"); //Задаем цвет текста
    }

    @Override
    public int reDraw(IArea area) {
        this.clearScreen();
        //TODO
        return 0;
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
        for (int clear = 0; clear < 500; clear++) {
            System.out.println("\b");
        }
    }

}
