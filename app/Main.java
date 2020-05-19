import bgs.Application;
import bgs.visual.ChessConsoleVisual;
import bgs.visual.src.IVisual;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;

/**
 * @author Roman
 */
public class Main {

    public static @Nullable String configExplanation(@NotNull IVisual visual) {
        if (!visual.showMessage("Load config from ini-file?[Y/N]", true)
                .toLowerCase()
                .equals("n")) {
            while (true) {
                String filePath = visual.showMessage("Please, write config file path.", true);
                File file = new File(filePath.trim());
                if (file.exists() && file.isFile() && file.canRead())
                    return filePath;
            }
        } else return null;
    }

    public static void main(String[] args) {
        IVisual chessConsoleVisual = new ChessConsoleVisual();
        new Application(chessConsoleVisual).run(configExplanation(chessConsoleVisual));
    }
}
