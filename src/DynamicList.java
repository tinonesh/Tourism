import java.util.Arrays;

public class DynamicList<T> {
    private Object[] data;
    private int size;

    public DynamicList() {
        data = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size >= data.length) {
            data = Arrays.copyOf(data, data.length * 2);
        }
        data[size++] = item;
    }

    public T get(int index) {
        if (index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    public int size() { return size; }

    public void clear() { size = 0; }

    public void set(int index, T item) {
        if (index >= size) throw new IndexOutOfBoundsException();
        data[index] = item;
    }
}
