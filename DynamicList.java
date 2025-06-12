public class DynamicList<T> {
    private Object[] data;
    private int size;

    public DynamicList() {
        data = new Object[10];
        size = 0;
    }

    public void add(T item) {
        if (size == data.length) resize();
        data[size++] = item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    public int size() { return size; }

    private void resize() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        System.arraycopy(data, index + 1, data, index, size - index - 1);
        size--;
    }

    public void sort(java.util.Comparator<T> comparator) {
        for (int i = 1; i < size; i++) {
            T key = (T) data[i];
            int j = i - 1;
            while (j >= 0 && comparator.compare((T) data[j], key) > 0) {
                data[j + 1] = data[j];
                j--;
            }
            data[j + 1] = key;
        }
    }
}