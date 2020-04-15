package app;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import visual.ChessConsoleVisual;
import visual.src.Interfaces.IVisual;

import java.io.File;

/**
 * @author Roman
 */
public class EntryPoint {

    /**
     * @return String
     */
    public static @Nullable String configExplanation(@NotNull IVisual visual) {
        if (!visual.showMessage("ЗАГРУЗИТЬ ПАРАМЕТРЫ ИЗ КОНФИГА?\nY/N", true, false)
                .toUpperCase()
                .equals("N")) {
            while (true) {
                String filePath = visual.showMessage("Введи путь к ini файлу конфига.", true, false);
                File file = new File(filePath.trim());
                if (file.isFile() && file.exists() && file.canRead())
                    return filePath;
            }
        } else return null;
    }

    /**
     * @param args Аргументы передаваемые в метод
     */
    public static void main(String[] args) {
        IVisual chessConsoleVisual = new ChessConsoleVisual();
        new Application(chessConsoleVisual).run(configExplanation(chessConsoleVisual));
    }
}
