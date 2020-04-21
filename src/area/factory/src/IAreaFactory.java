package area.factory.src;

import area.IArea;

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
