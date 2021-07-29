package com.wl;

import com.wl.array.DynamicArray;
import com.wl.list.DoubleLinkedList;
import com.wl.list.LinkedList;

public class Main {
    public static void main(String[] argv){
        testDoubleLinkedList();
    }

    private static void testDynamicArray(){
        DynamicArray<Integer> dynamicArray = new DynamicArray<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        dynamicArray.addArray(array);
        System.out.println(dynamicArray);
        for (int i = 0; i < 50; i++) {
            dynamicArray.remove(0);
        }
        System.out.println(dynamicArray);
    }

    private static void testLinkedList(){
        LinkedList<Integer> list = new LinkedList<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        list.addArray(array);
        System.out.println(list);
        System.out.println("index : " + 50 + " value : " + list.get(50));
        System.out.println("The result of list containing " + 50 + " is " + list.contains(50));
        System.out.println("index : " + list.indexOf(50) + " value : " + list.get(50));
        for (int i = 0; i < 50; i++) {
            list.remove(50);
        }
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }

    private static void testDoubleLinkedList(){
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        Integer[] array = new Integer[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }
        list.addArray(array);
        System.out.println(list);
        System.out.println("index : " + 50 + " value : " + list.get(50));
        System.out.println("The result of list containing " + 50 + " is " + list.contains(50));
        System.out.println("index : " + list.indexOf(50) + " value : " + list.get(50));
        for (int i = 0; i < 50; i++) {
            list.remove(50);
        }
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }
}
