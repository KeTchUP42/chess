package area.snapshot;

import objects.IObject;

/**
 * @author Roman
 */
public class SnapShot {

    /**
     * Данные о последнем ходе
     */
    protected IObject movedObject;
    protected IObject destroyedObject;

    /**
     * Возвращает последний сдвинутый объект
     *
     * @return IObject
     */
    public IObject getMovedObject() {
        return this.movedObject;
    }

    /**
     * Задаем последний двигаемый объект
     *
     * @param object Номер клетки в которой находился сдвинутый объект
     */
    public SnapShot setMovedObject(IObject object) {
        try {
            this.movedObject = object != null ? object.clone() : null;
        } catch (CloneNotSupportedException ignored) {
        }
        return this;
    }

    /**
     * Возвращает последний уничтоженный объект
     *
     * @return IObject
     */
    public IObject getDestroyedObject() {
        return this.destroyedObject;
    }

    /**
     * Задаем последний уничтоженный объект
     *
     * @param object IObject
     */
    public SnapShot setDestroyedObject(IObject object) {
        try {
            this.destroyedObject = object != null ? object.clone() : null;
        } catch (CloneNotSupportedException ignored) {
        }
        return this;
    }
}
