package visual.src;

import area.IArea;

/**
 * @author Roman
 */
public interface IVisual {

    /**
     * Перерисовка области
     */
    void Draw(IArea area);

    /**
     * Метод реализует логику общения с игроком
     *
     * @param message   Сообщение
     * @param getAnswer Получить ответ?
     * @return String ответа/null
     */
    String showMessage(String message, boolean getAnswer, boolean afterClear);
}
