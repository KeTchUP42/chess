package area.factory.src.Interfaces;

import area.Interfaces.IArea;

public interface IAreaFactory {
    /**
     * @return Возвращает готовую доску
     */
    IArea getStandardArea();

    /**
     * @return Возвращает тестовую доску
     */
    IArea getTestArea();
}
