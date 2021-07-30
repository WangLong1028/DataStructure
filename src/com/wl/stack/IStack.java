package com.wl.stack;

public interface IStack<E> {

    // 元素数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 入栈
    void push(E e);

    // 出栈
    E pop();

    // 获取栈顶数据,但不出栈
    E peek();

    // 清空栈
    void clear();
}
