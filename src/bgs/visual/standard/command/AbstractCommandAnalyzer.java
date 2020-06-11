package bgs.visual.standard.command;

import bgs.visual.src.IVisual;

import java.util.Scanner;

/**
 * @author Roman
 */
public abstract class AbstractCommandAnalyzer {
    protected final String CONSOLE_MODE_COMMAND = "console_mode";
    protected final String CONSOLE_EXIT_COMMAND = "exit";
    protected final IVisual visual;

    public AbstractCommandAnalyzer(IVisual visual) {
        this.visual = visual;
    }

    public boolean analyze(String command) {
        if (command.equals(CONSOLE_EXIT_COMMAND)) System.exit(0);
        if (command.equals(CONSOLE_MODE_COMMAND)) return this.console_mode();
        return false;
    }

    /**
     * Method sets base command analyze interface
     */
    protected abstract void commandAnalyze(String command);

    protected boolean console_mode() {
        this.visual.showMessage("Console mode activated, input \"off\" to exit.", false);
        while (true) {
            String message = new Scanner(System.in).nextLine().trim();
            if (message.equals("off") || message.equals(CONSOLE_MODE_COMMAND)) {
                this.visual.showMessage("Console mode turned off.", false);
                break;
            }
            this.commandAnalyze(message);
        }
        return true;
    }
}
