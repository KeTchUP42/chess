package bgs.visual.standard;

import bgs.area.IArea;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.util.Scanner;

/**
 * @author Roman
 */
public class ConsoleVisual implements IVisual {

    @Override
    public void Draw(@NotNull IArea area) {
    }

    @Override
    public String showMessage(String message, boolean getAnswer) {
        System.out.println(message);
        String result = null;
        if (getAnswer) {
            Scanner in = new Scanner(System.in);
            while (true) {
                result = in.nextLine().trim();
                if (new CommandAnalyzer(this).analyze(result.toLowerCase())) {
                    System.out.println("\n" + message);
                    continue;
                }
                break;
            }
        }
        return result;
    }

    protected void clearConsole() {
        for (int index = 0; index < 3; index++) {
            System.out.println("\b");
        }
    }
}
