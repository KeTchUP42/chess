package bgs.setup.alias;

import bgs.setup.alias.Interfaces.IAliasList;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public final class AreaAliasList implements IAliasList {

    @Contract(value = " -> new", pure = true)
    @Override
    public String[] @NotNull [] getAliasList() {
        return new String[][]
                {
                        new String[]{"test", "bgs.area.factory.BoardFactory", "createTestArea"},
                        new String[]{"standard", "bgs.area.factory.BoardFactory", "createStandardArea"},
                        new String[]{"", "bgs.area.factory.BoardFactory", "createStandardArea"}
                };
    }
}
