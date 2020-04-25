import setup.INI.INIParser;
import setup.recipient.ConfigRecipient;
import src.AbstractApplication;
import visual.src.IVisual;

import java.io.IOException;

/**
 * @author Roman
 */
class Application extends AbstractApplication {

    public Application(IVisual Visual) {
        super(Visual);
    }

    public void run(String configFilePathOrNull) {
        try {
            this.loadSettings(configFilePathOrNull, new String[]{
                    "areaType",
                    "firstPlayerColor", "firstPlayerType", "firstPlayerName",
                    "secondPlayerType", "secondPlayerName",
                    "logFilePath"
            });
            this.runGame();
        } catch (Exception | Error e) {
            this.Visual.showMessage(e.getMessage(), false, false);
        } finally {
            this.Visual.showMessage("Exiting...", false, false);
        }
    }

    /**
     * null - player enters configs
     *
     * @throws IOException Error output
     */
    protected void loadSettings(String configPath, String[] configFields) throws IOException {
        if (configPath != null) {
            this.applySettings(new INIParser(configPath).loadConfig(configFields));
        } else {
            this.applySettings(new ConfigRecipient(this.Visual).getPlayerConfigs(configFields));
        }
    }
}
