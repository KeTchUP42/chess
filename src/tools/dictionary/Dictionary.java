package tools.dictionary;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * @author Roman
 */
public class Dictionary<Type> {

    protected ArrayList<Type> value = new ArrayList<>();
    protected ArrayList<String> names = new ArrayList<>();

    public void setItem(Type object, String name) {
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

    public Type getItem(String name) throws NoSuchElementException {
        for (int index = 0; index < this.names.size(); index++) {
            if (this.names.get(index).equals(name)) {
                return this.value.get(index);
            }
        }
        throw new NoSuchElementException();
    }

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
