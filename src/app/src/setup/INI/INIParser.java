package app.src.setup.INI;

import app.src.Abstract.ConfigFields;
import app.src.setup.INI.Abstract.AbstractINIParser;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author Roman
 */
public class INIParser extends AbstractINIParser {

    public INIParser(String filepath) throws IOException {
        super(filepath);
    }

    /**
     * Метод возвращает массив значений полученных из конфига
     *
     * @return Возвращает массив значений из ини файла
     */
    public String[] loadConfig(String @NotNull [] configFields) {
        this.setColorConfig();
        return new String[]{
                this.ini.get("DefaultSettings", configFields[ConfigFields.AREA_TYPE], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.FIRST_PLAYER_COLOR], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.FIRST_PLAYER_TYPE], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.FIRST_PLAYER_NAME], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.SECOND_PLAYER_TYPE], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.SECOND_PLAYER_NAME], String.class),
                this.ini.get("DefaultSettings", configFields[ConfigFields.LOG_FILE_PATH], String.class),
        };
    }

}
