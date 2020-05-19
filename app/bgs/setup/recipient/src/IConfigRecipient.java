package bgs.setup.recipient.src;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public interface IConfigRecipient {

    /**
     * Method contains config input logic
     */
    String[] findOutPlayersConfig(@NotNull String[] configItems);
}
