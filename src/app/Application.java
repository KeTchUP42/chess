package app;

import app.src.Abstract.AbstractApplication;
import visual.src.Interfaces.IVisual;

/**
 * @author Roman
 */
class Application extends AbstractApplication {


    /**
     * Задается визуал
     *
     * @param Visual Объект заключающий в себе функционал визуала
     */
    public Application(IVisual Visual) {
        super(Visual);
    }

    /**
     * Запуск приложения
     */
    public void run() {
        try {
            this.loadConfigFromFile(this.Visual.configClarification());
            this.gameRun();
        } catch (Exception | Error e) {
            this.Visual.sendMessage(e.getMessage(), false, false);
        } finally {
            this.Visual.sendMessage("Завершение работы...", false, false);
        }
    }
}
