package com.wl.tree;

public interface IBinarySearchTree<E> {

    // 元素数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 添加元素
    void add(E e);

    // 删除元素
    void remove(E e);

    // 清除所有元素
    void clear();

    // 是否包含元素
    boolean contain(E e);
}
