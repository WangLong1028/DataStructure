package com.wl.list;

public class DoubleLinkedList<E> implements ILinkedList<E> {

    // 根节点
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
            cur= cur.next;
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
        connectTwoNode(cur, newNode);
    }

    @Override
    public E get(int index) {
        return moveTo(index).value;
    }

    @Override
    public E set(int index, E e) {
        Node<E> cur = moveTo(index);
        E returnE = cur.value;
        cur.value = e;
        return returnE;
    }

    @Override
    public void add(int index, E e) {
        size++;
        if (index == 0) {
            if (header == null) {
                header = new Node<>();
                header.value = e;
                return;
            }
            Node<E> newNode = new Node<>();
            newNode.value = e;
            connectTwoNode(newNode, header);
            header = newNode;
            return;
        }
        Node<E> parent = moveTo(index - 1);
        Node<E> child = parent.next;
        Node<E> newNode = new Node<>();
        newNode.value = e;
        connectTwoNode(parent, newNode);
        if (child != null) {
            connectTwoNode(newNode, child);
        }
    }

    @Override
    public void addArray(E[] array) {
        for (E e : array) {
            add(e);
        }
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            Node<E> child = header.next;
            E returnE = header.value;
            header = child;
            size--;
            return returnE;
        }
        Node<E> cur = moveTo(index);
        Node<E> parent = cur.last;
        Node<E> child = cur.next;
        E returnE = cur.value;
        if (child != null) {
            connectTwoNode(parent, child);
        } else {
            parent.next = null;
        }
        size--;
        return returnE;
    }

    @Override
    public int indexOf(E e) {
        int index = 0;
        Node<E> cur = header;
        while (cur != null){
            if (cur.value.equals(e)){
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
        if (header == null){
            return;
        }
        Node<E> cur = header;
        Node<E> next = cur.next;
        cur.next = null;
        while (next != null){
            Node<E> temp = next.next;
            connectTwoNode(next, cur);
            cur = next;
            next = temp;
        }
        header = cur;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[ ");
        DoubleLinkedList.Node<E> cur = header;
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

    private Node<E> moveTo(int index) {
        rangeCheck(index);
        Node<E> cur = header;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException("Index out of range : index : " + index + " size : " + size);
        }
    }

    private void connectTwoNode(Node<E> last, Node<E> next) {
        last.next = next;
        next.last = last;
    }

    private static class Node<E> {
        public Node<E> last;
        public Node<E> next;
        public E value;
    }
}
