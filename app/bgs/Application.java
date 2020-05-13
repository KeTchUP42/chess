package bgs;

import bgs.setup.INI.INIParser;
import bgs.setup.INI.configList.IConfigList;
import bgs.setup.INI.configList.InIConfigList;
import bgs.setup.recipient.ConfigRecipient;
import bgs.src.AbstractApplication;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Roman
 */
public class Application extends AbstractApplication {

    public Application(IVisual Visual) {
        super(Visual);
    }

    /**
     * @param configFilePathOrNull
     */
    public void run(String configFilePathOrNull) {
        try {
            this.loadSettings(configFilePathOrNull, new InIConfigList());
            this.runGame();
        } catch (Exception | Error exception) {
            this.Visual.showMessage(exception.getMessage(), false);
            System.exit(-1);
        } finally {
            this.Visual.showMessage("Exiting...", false);
        }
    }

    /**
     * null - player enters config fields
     *
     * @throws IOException
     */
    protected void loadSettings(String configPath, @NotNull IConfigList configList) throws Exception {
        if (configPath != null) {
            this.applySettings(new INIParser(configPath).loadConfig(configList));
        } else {
            this.applySettings(new ConfigRecipient(this.Visual).findOutPlayersConfig(configList.getList()));
        }
    }
}
