package com.wl.array;

@SuppressWarnings("unchecked")
public class DynamicArray<E> implements IDynamicArray<E> {

    // 储存元素数量
    private int size;

    // 数组
    private E[] array;

    // 默认数组大小
    private static final int DEFAULT_ARRAY_SIZE = 10;

    // 数组增长系数
    private static final float AUTO_INCREMENT_FACTOR = 1.5f;

    // 寻找失败返回的索引
    public static final int FIND_FAILURE_CODE = -1;

    public DynamicArray() {
        this(DEFAULT_ARRAY_SIZE);
    }

    public DynamicArray(int defaultArraySize) {
        this.array = (E[]) new Object[defaultArraySize];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(E e) {
        for (E e1 : array) {
            if (e1.equals(e)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void add(E e) {
        size++;
        checkSize();

        array[size - 1] = e;
    }

    @Override
    public E get(int index) {
        int targetIndex = checkIndex(index);
        return array[targetIndex];
    }

    @Override
    public E set(int index, E e) {
        int targetIndex = checkIndex(index);
        E returnE = array[targetIndex];
        array[targetIndex] = e;
        return returnE;
    }

    @Override
    public void add(int index, E e) {
        size++;
        checkSize();

        int targetIndex = checkIndex(index);
        moveRearward(targetIndex);
        array[targetIndex] = e;
    }

    @Override
    public void addArray(E[] array) {
        for (E e : array) {
            add(e);
        }
    }

    @Override
    public E remove(int index) {
        if (size <= 0) {
            return null;
        }

        int targetIndex = checkIndex(index);
        E returnE = array[targetIndex];
        for (int i = targetIndex; i < size; i++) {
            if (i + 1 >= size) {
                break;
            }
            array[i] = array[i + 1];
        }
        size--;
        checkSize();
        return returnE;
    }

    // 寻找失败返回-1
    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(array[i])) {
                return i;
            }
        }
        return FIND_FAILURE_CODE;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        this.array = (E[]) new Object[DEFAULT_ARRAY_SIZE];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(array[i]).append(" ");
                break;
            }
            stringBuilder.append(array[i]).append(", ");
        }
        stringBuilder.append("]");
        stringBuilder.append("   size = ").append(size);
        stringBuilder.append("   length = ").append(array.length);
        return stringBuilder.toString();
    }

    // 检查是否越界,如果越界,则返回最后一个元素
    private int checkIndex(int index) {
        return Math.min(index, size - 1);
    }

    // 检查数组元素大小以确认是否放大
    private void checkSize() {
        if (size > array.length) {
            resizeArrayLarger();
        }
        if (size < array.length / AUTO_INCREMENT_FACTOR) {
            resizeArraySmaller();
        }
    }

    // 增大数组
    private void resizeArrayLarger() {
        int newArraySize = ((int) (array.length * AUTO_INCREMENT_FACTOR));
        E[] newArray = (E[]) new Object[newArraySize];
        System.arraycopy(array, 0, newArray, 0, array.length);
        this.array = newArray;
    }

    // 缩小数组
    private void resizeArraySmaller() {
        int newArraySize = ((int) (array.length / AUTO_INCREMENT_FACTOR));
        E[] newArray = (E[]) new Object[newArraySize];
        System.arraycopy(array, 0, newArray, 0, newArray.length);
        this.array = newArray;
    }

    // 数组元素从该索引(包括该索引)后移一格
    private void moveRearward(int index) {
        if (size - (index + 1) >= 0) System.arraycopy(array, index + 1 - 1, array, index + 1, size - (index + 1));
        array[index] = null;
    }
}
