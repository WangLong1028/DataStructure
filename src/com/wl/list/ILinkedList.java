package com.wl.list;

public interface ILinkedList<E> {

    // 元素数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 是否包含某个元素
    boolean contains(E e);

    // 添加某个元素到最后面
    void add(E e);

    // 根据索引获取元素
    E get(int index);

    // 设置某个位置的元素
    E set(int index, E e);

    // 将元素添加至目标索引处
    void add(int index, E e);

    // 添加数组
    void addArray(E[] array);

    // 根据索引删除元素
    E remove(int index);

    // 查看元素的位置
    int indexOf(E e);

    // 清除所有元素
    void clear();

    // 反转链表
    void reverse();
}
