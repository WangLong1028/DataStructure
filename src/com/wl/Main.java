package com.wl;

import com.wl.array.DynamicArray;
import com.wl.list.DoubleLinkedList;
import com.wl.list.LinkedList;
import com.wl.stack.Stack;
import com.wl.tree.BinarySearchTree;
import com.wl.tree.printer.LevelOrderPrinter;
import com.wl.tree.printer.Printer;

public class Main {
    public static void main(String[] argv){
        testBinarySearchTree();
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

    private static void testStack(){
        Stack<Character> stack = new Stack<>();
        stack.push('a');
        stack.push('b');
        stack.push('c');
        for (int i = 0; i < 3; i++) {
            System.out.println(stack.pop());
        }
    }

    private static void testBinarySearchTree(){
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        Integer[] array = new Integer[]{36, 68, 26, 67, 32, 4, 34, 11, 76, 3, 41, 46, 73, 100, 96, 44, 17, 45};
        tree.addArray(array);
        Printer printer = new LevelOrderPrinter(tree);
        printer.println();
    }
}
