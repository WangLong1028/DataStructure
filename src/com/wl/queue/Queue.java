package com.wl.queue;

import com.wl.list.LinkedList;

public class Queue <E> implements IQueue<E>{

    // 使用链表作为数据载体
    private LinkedList<E> data;

    public Queue() {
        data = new LinkedList<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void clear() {
        data.clear();
    }

    @Override
    public void enQueue(E e) {
        data.add(e);
    }

    @Override
    public E deQueue() {
        return data.remove(0);
    }

    @Override
    public E front() {
        return data.get(0);
    }
}
