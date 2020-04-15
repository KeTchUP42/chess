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
}
