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
     * @param aliasList alias list
     * @param alias     alias
     * @return new object
     * @throws ReflectiveOperationException some exception
     * @throws AliasNotFoundException       some exception
     */
    Type generate(@NotNull IAliasList aliasList, String alias)
            throws ReflectiveOperationException, AliasNotFoundException;
}
