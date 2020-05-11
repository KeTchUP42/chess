package bgs.setup.alias.Interfaces;

import bgs.setup.alias.exception.AliasNotFoundException;
import org.jetbrains.annotations.NotNull;


/**
 * @author Roman
 */
public interface IGenerator<Type> {

    /**
     * Method generates new <Type> from alias
     *
     * @param aliasList
     * @param alias
     * @return
     * @throws ReflectiveOperationException
     * @throws AliasNotFoundException
     */
    Type generate(@NotNull IAliasList aliasList, String alias)
            throws ReflectiveOperationException, AliasNotFoundException;
}
