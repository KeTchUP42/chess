package bgs;

import bgs.setup.Ini.IniParser;
import bgs.setup.Ini.configList.IConfigList;
import bgs.setup.Ini.configList.InIConfigList;
import bgs.setup.recipient.ConfigRecipient;
import bgs.src.AbstractApplication;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * @author Roman
 */
public class Application extends AbstractApplication {

    public Application(IVisual Visual) {
        super(Visual);
    }

    public void run(String configFilePath) {
        try {
            this.loadSettings(configFilePath, new InIConfigList());
            this.runGame();
        } catch (Exception | Error exception) {
            this.Visual.showMessage(exception.getMessage(), false);
            System.exit(-1);
        } finally {
            this.Visual.showMessage("Exiting...", false);
        }
    }

    /**
     * Method load setting from ini file or console
     * null - player enters config fields
     */
    private void loadSettings(String configPath, @NotNull IConfigList configList) throws Exception {

        if (configPath != null && new File(configPath).exists()) {
            this.applySettings(new IniParser(configPath).loadConfig(configList));
        } else {
            this.applySettings(new ConfigRecipient(this.Visual).findOutPlayersConfig(configList.getList()));
        }
    }
}
