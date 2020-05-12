package bgs.setup.alias;

import bgs.setup.alias.Interfaces.IAliasList;

/**
 * @author Roman
 */
public final class AreaAliasList implements IAliasList {

    @Override
    public String[][] getAliasList() {
        return new String[][]
                {
                        new String[]{"test", "bgs.area.factory.BoardFactory", "createTestArea"},
                        new String[]{"standard", "bgs.area.factory.BoardFactory", "createStandardArea"},
                        new String[]{"", "bgs.area.factory.BoardFactory", "createStandardArea"}
                };
    }
}
