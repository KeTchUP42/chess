package app.src.setup.src.Interfaces;

import org.jetbrains.annotations.NotNull;

/**
 * @author Roman
 */
public interface IConfigRecipient {

    /**
     * Метод реализует логику получения информации от игрока
     *
     * @return Массив для loadSettings
     */
    String[] getPlayerConfigs(@NotNull String[] configItems);
}
