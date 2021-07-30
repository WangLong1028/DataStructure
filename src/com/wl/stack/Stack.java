package com.wl.stack;

import com.wl.array.DynamicArray;

public class Stack<E> implements IStack<E>{

    // 使用动态数组作为载体储存栈元素
    private DynamicArray<E> data;

    public Stack(){
        data = new DynamicArray<>();
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
    public void push(E e) {
        data.add(e);
    }

    @Override
    public E pop() {
        return data.remove(data.size() - 1);
    }

    @Override
    public E peek() {
        return data.get(data.size() - 1);
    }

    @Override
    public void clear() {
        data.clear();
    }
}
