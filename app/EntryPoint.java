import bgs.Application;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import visual.ChessConsoleVisual;
import visual.src.IVisual;

import java.io.File;

/**
 * @author Roman
 */
public class EntryPoint {

    public static @Nullable String configExplanation(@NotNull IVisual visual) {
        if (!visual.showMessage("Load config from ini-file?\nY/N", true)
                .toUpperCase()
                .equals("N")) {
            while (true) {
                String filePath = visual.showMessage("Please, write config file path.", true);
                File file = new File(filePath.trim());
                if (file.isFile() && file.exists() && file.canRead())
                    return filePath;
            }
        } else return null;
    }

    public static void main(String[] args) {
        IVisual chessConsoleVisual = new ChessConsoleVisual();
        new Application(chessConsoleVisual).run(configExplanation(chessConsoleVisual));
    }
}
