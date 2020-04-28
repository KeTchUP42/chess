package visual.standard;

import area.IArea;
import visual.src.IVisual;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Roman
 */
public class ConsoleVisual implements IVisual {

    @Override
    public void Draw(IArea area) {
    }

    @Override
    public String showMessage(String message, boolean getAnswer) {
        System.out.println(message);
        String dialogResult = null;
        if (getAnswer) {
            Scanner in = new Scanner(System.in);
            //Base console func
            while (true) {
                dialogResult = in.nextLine().trim();
                if (this.consoleAction(dialogResult.toLowerCase())) {
                    System.out.println("\n" + message);
                    continue;
                }
                break;
            }
        }
        return dialogResult;
    }

    /**
     * Console actions
     *
     * @param input Output line
     */
    public boolean consoleAction(String input) {
        if (input.equals("exit") || input.equals("die")) System.exit(0);
        if (input.equals("help")) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File("README.md")));
                String line = reader.readLine();
                while (line != null) {
                    this.showMessage(line, false);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                this.showMessage(e.getMessage(), false);
            }
            return true;
        }
        //
        return false;
    }

    protected void cleanConsole() {
        for (int clear = 0; clear < 3; clear++) {
            System.out.println("\b");
        }
    }
}
