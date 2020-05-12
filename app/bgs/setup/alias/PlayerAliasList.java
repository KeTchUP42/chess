package bgs.setup.alias;

import bgs.setup.alias.Interfaces.IAliasList;

/**
 * @author Roman
 */
public final class PlayerAliasList implements IAliasList {

    @Override
    public String[][] getAliasList() {
        return new String[][]
                {
                        new String[]{"player", "bgs.brains.player.Player"},
                        new String[]{"bot_0", "bgs.brains.bots.Bot_0"},
                        new String[]{"", "bgs.brains.bots.Bot_0"}
                };
    }
}
