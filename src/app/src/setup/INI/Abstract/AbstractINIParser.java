package app.src.setup.INI.Abstract;

import area.factory.src.GameColors;
import org.ini4j.Wini;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public abstract class AbstractINIParser {

    /**
     * Wini объект из библиотеки (см. lib/*)
     */
    protected final Wini ini;

    public AbstractINIParser(String filepath) throws IOException {
        this.ini = new Wini(new File(filepath));
    }

    /**
     * Метод возвращает массив значений полученных из конфига
     *
     * @return Возвращает массив значений из ини файла
     */
    public abstract String[] loadConfig(String @NotNull [] configFields);

    /**
     * Метод настройки базовых цветов в GameColors
     */
    protected void setColorConfig() {
        try {
            Color firstColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "FirstColor", String.class)).get(null);
            Color secondColor = (Color) Color.class.getField(
                    this.ini.get("ColorSettings", "SecondColor", String.class)).get(null);
            if (firstColor == secondColor) return;
            GameColors.firstStepColor = firstColor;
            GameColors.secondStepColor = secondColor;
        } catch (Exception ignored) {
        }
    }
}
