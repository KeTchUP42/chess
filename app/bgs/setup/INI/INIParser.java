package bgs.setup.INI;

import bgs.src.ConfigFields;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Roman
 */
public class INIParser extends AbstractINIParser {

    public INIParser(String filepath) throws IOException {
        super(filepath);
    }

    public String[] loadConfig(String @NotNull [] configFields) {
        this.applyColorConfig();
        return new String[]{
                this.ini.get("AreaSettings", configFields[ConfigFields.AREA_TYPE], String.class),
                this.ini.get("StepOrderSettings", configFields[ConfigFields.STEP_ORDER], String.class),
                this.ini.get("FirstPlayerSettings", configFields[ConfigFields.FIRST_PLAYER_TYPE], String.class),
                this.ini.get("FirstPlayerSettings", configFields[ConfigFields.FIRST_PLAYER_NAME], String.class),
                this.ini.get("SecondPlayerSettings", configFields[ConfigFields.SECOND_PLAYER_TYPE], String.class),
                this.ini.get("SecondPlayerSettings", configFields[ConfigFields.SECOND_PLAYER_NAME], String.class),
                this.ini.get("LogSettings", configFields[ConfigFields.LOG_FILE_PATH], String.class),
        };
    }

}
