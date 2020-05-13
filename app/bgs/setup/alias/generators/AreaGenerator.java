package bgs.setup.alias.generators;

import bgs.area.IArea;
import bgs.setup.alias.Interfaces.IAliasList;
import bgs.setup.alias.Interfaces.IGenerator;
import bgs.setup.alias.exceptions.AliasNotFoundException;
import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public class AreaGenerator implements IGenerator<IArea> {

    @Override
    public IArea generate(@NotNull IAliasList aliasList, final String alias) throws ReflectiveOperationException, AliasNotFoundException {

        for (String[] array : aliasList.getAliasList()) {
            if (array[0].equals(alias)) {
                Class<?> aClass = Class.forName(array[1]);
                return (IArea) aClass.getMethod(array[2]).invoke(aClass.getDeclaredConstructor().newInstance());
            }
        }
        throw new AliasNotFoundException("Check area's aliases, no such alias!");
    }
}
