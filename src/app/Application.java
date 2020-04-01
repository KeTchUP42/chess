package app;

import app.src.Abstract.AbstractApplication;
import visual.src.Interfaces.IVisual;

class Application extends AbstractApplication {


    /**
     * Задается визуал
     *
     * @param visual Объект заключающий в себе функционал визуала
     */
    public Application(IVisual visual) {
        super(visual);
    }

    /**
     * Запуск приложения
     */
    public void run() {
        try {
            this.loadConfigFromFile(this.visual.ConfigClarification());
            this.gameRun();
        } catch (Exception | Error e) {
            this.visual.sendMessage(e.getMessage(), false, false);
        } finally {
            this.visual.sendMessage("Завершение работы...", false, false);
        }
    }
}
