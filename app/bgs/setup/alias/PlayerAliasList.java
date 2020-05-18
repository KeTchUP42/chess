package bgs.setup.alias;

import bgs.setup.alias.Interfaces.IAliasList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public final class PlayerAliasList implements IAliasList {

    @Contract(value = " -> new", pure = true)
    @Override
    public String[] @NotNull [] getAliasList() {
        return new String[][]
                {
                        new String[]{"player", "bgs.brains.player.Player"},
                        new String[]{"bot_0", "bgs.brains.bots.Bot_0"},
                        new String[]{"", "bgs.brains.bots.Bot_0"}
                };
    }
}
