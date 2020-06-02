package bgs.setup.alias.generators;

import bgs.area.IArea;
import bgs.setup.alias.Interfaces.IAliasList;
import bgs.setup.alias.Interfaces.IGenerator;
import bgs.setup.alias.exceptions.AliasNotFoundException;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Constructor;

/**
 * @author Roman
 */
public class PlayerGenerator implements IGenerator<Constructor<?>> {

    @Override
    public Constructor<?> generate(@NotNull IAliasList aliasList, final String alias) throws ReflectiveOperationException, AliasNotFoundException {

        for (String[] array : aliasList.getAliasList()) {
            if (array[0].equals(alias)) {
                Class<?>[] params = {IArea.class, Color.class, IVisual.class, String.class};
                return Class.forName(array[1]).getConstructor(params);
            }
        }
        throw new AliasNotFoundException("Check player's aliases, no such alias!");
    }
}
