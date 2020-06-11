package bgs.visual.standard.command;

import bgs.setup.alias.AreaAliasList;
import bgs.setup.alias.PlayerAliasList;
import bgs.visual.src.IVisual;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author Roman
 */
public final class CommandAnalyzer extends AbstractCommandAnalyzer {
    public CommandAnalyzer(IVisual visual) {
        super(visual);
    }

    /**
     * Method overrides abstract method with reflection methods call
     */
    protected void commandAnalyze(String command) {
        try {
            Method method = CommandAnalyzer.class.getDeclaredMethod(command);
            method.setAccessible(true);
            method.invoke(this);
        } catch (Exception ignored) {
            this.visual.showMessage("Hey, no such command!", false);
        }
    }

    private void player_alias() {
        for (String[] alias :
                new PlayerAliasList().getAliasList()) {
            this.visual.showMessage("*: '" + alias[0] + "' creates " + alias[1], false);
        }
    }

    private void area_alias() {
        for (String[] alias :
                new AreaAliasList().getAliasList()) {
            this.visual.showMessage("*: '" + alias[0] + "' calls method " + alias[2] + " in " + alias[1], false);
        }
    }

    private void help() {
        this.visual.showMessage("Available console commands:", false);
        for (Method method : CommandAnalyzer.class.getDeclaredMethods()
        ) {
            if (method.getReturnType().getName().equals("void") && method.getModifiers() == Modifier.PRIVATE)
                this.visual.showMessage("* " + method.getName(), false);
        }
    }
}
