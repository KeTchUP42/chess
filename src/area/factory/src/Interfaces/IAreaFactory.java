package area.factory.src.Interfaces;

import area.Interfaces.IArea;

/**
 * @author Roman
 */
public interface IAreaFactory {

    /**
     * @return Возвращает стандартную область
     */
    IArea createStandardArea();

    /**
     * @return Возвращает тестовую область
     */
    IArea createTestArea();
}
