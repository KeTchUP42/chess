package bgs.setup.alias.Interfaces;

import bgs.setup.alias.exceptions.AliasNotFoundException;
import org.jetbrains.annotations.NotNull;


/**
 * @author Roman
 */
public interface IGenerator<Type> {

    /**
     * Method generates new <Type> from alias
     *
     * @throws ReflectiveOperationException
     * @throws AliasNotFoundException
     */
    Type generate(@NotNull IAliasList aliasList, String alias)
            throws ReflectiveOperationException, AliasNotFoundException;
}
