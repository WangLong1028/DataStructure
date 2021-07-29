package com.wl.list;

public class LinkedList<E> implements ILinkedList<E> {

    // 头节点
    private Node<E> header;

    // 数量
    private int size;

    // 寻找失败返回的索引
    public static final int FIND_FAILURE_CODE = -1;

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
        Node<E> cur = header;
        while (cur != null) {
            if (cur.value.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    @Override
    public void add(E e) {
        size++;
        if (header == null) {
            header = new Node<>();
            header.value = e;
            return;
        }
        Node<E> cur = header;
        while (cur.next != null) {
            cur = cur.next;
        }
        Node<E> newNode = new Node<>();
        newNode.value = e;
        cur.next = newNode;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return moveTo(index).value;
    }

    @Override
    public E set(int index, E e) {
        rangeCheck(index);
        Node<E> cur = moveTo(index);
        E returnE = cur.value;
        cur.value = e;
        return returnE;
    }

    @Override
    public void add(int index, E e) {
        rangeCheck(index);
        size++;

        if (index == 0) {
            Node<E> newNode = new Node<>();
            newNode.value = e;
            newNode.next = header;
            header = newNode;
            return;
        }

        Node<E> cur = moveTo(index - 1);
        Node<E> newNode = new Node<>();
        newNode.value = e;
        newNode.next = cur.next;
        cur.next = newNode;
    }

    @Override
    public void addArray(E[] array) {
        for (E e : array) {
            add(e);
        }
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        size--;
        if (index == 0) {
            E returnE = header.value;
            header = header.next;
            return returnE;
        }

        Node<E> parent = moveTo(index - 1);
        Node<E> cur = parent.next;
        Node<E> child = cur.next;

        E returnE = cur.value;
        parent.next = child;
        cur.next = null;

        return returnE;
    }

    @Override
    public int indexOf(E e) {
        Node<E> cur = header;
        int index = 0;
        while (cur != null) {
            if (cur.value.equals(e)) {
                return index;
            }
            index++;
            cur = cur.next;
        }
        return FIND_FAILURE_CODE;
    }

    @Override
    public void clear() {
        header = null;
        size = 0;
    }

    @Override
    public void reverse() {
        if (header == null) {
            return;
        }
        Node<E> cur = header;
        Node<E> next = header.next;
        cur.next = null;
        while (next != null) {
            Node<E> temp = next.next;
            next.next = cur;

            cur = next;
            next = temp;
        }
        header = cur;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        Node<E> cur = header;
        for (int i = 0; i < size; i++) {
            if (i == size - 1) {
                stringBuilder.append(cur.value).append(" ");
                break;
            }
            stringBuilder.append(cur.value).append(", ");
            cur = cur.next;
        }
        stringBuilder.append("]");
        stringBuilder.append("  size : ").append(size);
        return stringBuilder.toString();
    }

    // 检查索引是否越界
    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range : index : " + index + " size : " + size);
        }
    }

    // 移动到目标索引
    private Node<E> moveTo(int index) {
        Node<E> cur = header;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }


    private static class Node<E> {
        public E value;
        public Node<E> next;
    }
}
