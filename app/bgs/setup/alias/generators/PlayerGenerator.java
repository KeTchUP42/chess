package bgs.setup.alias.generators;

import bgs.brains.src.IChangeablePlayer;
import bgs.setup.alias.Interfaces.IAliasList;
import bgs.setup.alias.Interfaces.IGenerator;
import bgs.setup.alias.exception.AliasNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public class PlayerGenerator implements IGenerator<IChangeablePlayer> {

    @Override
    public IChangeablePlayer generate(@NotNull IAliasList aliasList, String alias) throws ReflectiveOperationException, AliasNotFoundException {

        for (String[] array : aliasList.getAliasList()) {
            if (array[0].equals(alias)) {
                return (IChangeablePlayer) Class.forName(array[1]).getDeclaredConstructor().newInstance();
            }
        }
        throw new AliasNotFoundException("Check player's aliases, no such alias!");
    }
}
