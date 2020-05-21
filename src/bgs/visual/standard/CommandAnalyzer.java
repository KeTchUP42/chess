package bgs.visual.standard;

import bgs.setup.alias.AreaAliasList;
import bgs.setup.alias.PlayerAliasList;
import bgs.visual.src.IVisual;

import java.lang.reflect.Method;
import java.util.Scanner;

public final class CommandAnalyzer {

    private final String CONSOLE_MODE_COMMAND = "console_mode";

    private final IVisual visual;

    public CommandAnalyzer(IVisual visual) {
        this.visual = visual;
    }

    public boolean analyze(String command) {
        if (command.equals("exit") || command.equals("die")) this.exit();
        if (command.equals(CONSOLE_MODE_COMMAND)) return this.console_mode();
        return false;
    }

    private void selfAnalyze(String command) {
        try {
            Method method = CommandAnalyzer.class.getDeclaredMethod(command);
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception ignored) {
            System.out.println("Hey, no such command!");
        }
    }

    private boolean player_alias() {
        for (String[] alias :
                new PlayerAliasList().getAliasList()) {
            this.visual.showMessage("*: '" + alias[0] + "' creates " + alias[1], false);
        }
        return true;
    }

    private boolean area_alias() {
        for (String[] alias :
                new AreaAliasList().getAliasList()) {
            this.visual.showMessage("*: '" + alias[0] + "' calls method " + alias[2] + " in " + alias[1], false);
        }
        return true;
    }

    private void exit() {
        System.exit(0);
    }

    private boolean console_mode() {
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
