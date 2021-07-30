package com.wl.queue;

public interface IQueue<E> {

    // 元素数量
    int size();

    // 是否为空
    boolean isEmpty();

    // 清空
    void clear();

    // 入队
    void enQueue(E e);

    // 出队
    E deQueue();

    // 获取第一个元素, 但不移除
    E front();
}
