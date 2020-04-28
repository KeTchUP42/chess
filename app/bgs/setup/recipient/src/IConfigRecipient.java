package bgs.setup.recipient.src;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public interface IConfigRecipient {

    /**
     * Method with config input logic
     *
     * @return data for loadSettings
     */
    String[] findOutPlayersConfig(@NotNull String[] configItems);
}
