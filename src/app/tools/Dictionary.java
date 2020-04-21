package app.tools;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * @author Roman
 */
public class Dictionary<Type> {

    /**
     * Данные
     */
    protected ArrayList<Type> value = new ArrayList<>();
    protected ArrayList<String> names = new ArrayList<>();

    /**
     * Метод добавляет новый элемент
     *
     * @param object IObject
     * @param name   String
     */
    public void setItem(@NotNull Type object, String name) {
        for (int index = 0; index < this.names.size(); index++) {
            if (this.names.get(index).equals(name)) {
                this.value.remove(index);
                this.value.add(index, object);
                return;
            }
        }
        this.names.add(name);
        this.value.add(object);
    }

    /**
     * Получаем элемент по имени
     *
     * @param name String
     * @return IObject
     */
    public Type getItem(String name) {
        for (int index = 0; index < this.names.size(); index++) {
            if (this.names.get(index).equals(name)) {
                return this.value.get(index);
            }
        }
        return null;
    }

    /**
     * Метод удаляет элемент
     *
     * @param name String
     * @return boolean
     */
    public boolean deleteItem(String name) {
        for (int index = 0; index < this.names.size(); index++) {
            if (this.names.get(index).equals(name)) {
                this.value.remove(index);
                this.names.remove(index);
                return true;
            }
        }
        return false;
    }
}
