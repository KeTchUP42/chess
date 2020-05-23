package bgs.setup.Ini;

import bgs.setup.Ini.configList.ConfigFields;
import bgs.setup.Ini.configList.IConfigList;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Roman
 */
public class IniParser extends AbstractIniParser {

    public IniParser(String filepath) throws IOException {
        super(filepath);
    }

    public String[] loadConfig(@NotNull IConfigList configList) {
        this.applyColorConfig(configList.getColorList());
        String[][] configFields = configList.getFullList();
        return new String[]{
                this.ini.get(configFields[ConfigFields.AREA_TYPE][0], configFields[ConfigFields.AREA_TYPE][1], String.class),
                this.ini.get(configFields[ConfigFields.STEP_ORDER][0], configFields[ConfigFields.STEP_ORDER][1], String.class),
                this.ini.get(configFields[ConfigFields.LOG_FILE_PATH][0], configFields[ConfigFields.LOG_FILE_PATH][1], String.class),
                this.ini.get(configFields[ConfigFields.FIRST_PLAYER_TYPE][0], configFields[ConfigFields.FIRST_PLAYER_TYPE][1], String.class),
                this.ini.get(configFields[ConfigFields.FIRST_PLAYER_NAME][0], configFields[ConfigFields.FIRST_PLAYER_NAME][1], String.class),
                this.ini.get(configFields[ConfigFields.SECOND_PLAYER_TYPE][0], configFields[ConfigFields.SECOND_PLAYER_TYPE][1], String.class),
                this.ini.get(configFields[ConfigFields.SECOND_PLAYER_NAME][0], configFields[ConfigFields.SECOND_PLAYER_NAME][1], String.class),
        };
    }
}
