package bgs.visual.standard;

import bgs.visual.src.IVisual;

import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * @author Roman
 */
abstract class AbstractCommandAnalyzer {
    protected final String CONSOLE_MODE_COMMAND = "console_mode";

    protected final IVisual visual;

    public AbstractCommandAnalyzer(IVisual visual) {
        this.visual = visual;
    }

    public boolean analyze(String command) {
        if (command.equals("exit")) System.exit(0);
        if (command.equals(CONSOLE_MODE_COMMAND)) return this.console_mode();
        return false;
    }

    protected void selfAnalyze(String command) {
        try {
            Method method = CommandAnalyzer.class.getDeclaredMethod(command);
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception ignored) {
            System.out.println("Hey, no such command!");
        }
    }

    protected boolean console_mode() {
        this.visual.showMessage("Console mode activated, input \"off\" to exit.", false);
        while (true) {
            String message = new Scanner(System.in).nextLine().trim();
            if (message.equals("off") || message.equals(CONSOLE_MODE_COMMAND)) {
                this.visual.showMessage("Console mode turned off.", false);
                break;
            }
            this.selfAnalyze(message);
        }
        return true;
    }
}
