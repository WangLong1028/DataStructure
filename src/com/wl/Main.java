package com.wl;

import com.wl.array.DynamicArray;
import com.wl.list.DoubleLinkedList;
import com.wl.list.LinkedList;
import com.wl.stack.Stack;
import com.wl.tree.AVLTree;
import com.wl.tree.BinarySearchTree;
import com.wl.tree.printer.InorderPrinter;
import com.wl.tree.printer.LevelOrderPrinter;
import com.wl.tree.printer.Printer;

public class Main {
    public static void main(String[] argv){
        testAVLTree();
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
        Integer[] array = new Integer[]{27, 76, 55, 46, 93, 80};
        tree.addArray(array);

        DynamicArray<Integer> traversalResult = new DynamicArray<>();
        tree.traversal((Integer integer) -> {
            traversalResult.add(integer);
            return false;
        }, BinarySearchTree.TRAVERSAL_MODE_LRD);

        System.out.println();
        System.out.println(traversalResult);

        Printer printer = new InorderPrinter(tree);
        printer.println();

        System.out.println();
        for (int i = 0; i < traversalResult.size() - 1; i++) {
            tree.remove(traversalResult.get(i));
        }
        printer.println();
    }

    private static void testAVLTree(){
        BinarySearchTree<Integer> tree = new AVLTree<>();
        Integer[] array = new Integer[]{39, 97, 70, 47, 57, 71, 8, 14, 58, 19, 49, 30, 62, 92, 94, 72, 82, 65};
        tree.addArray(array);

        DynamicArray<Integer> traversalResult = new DynamicArray<>();
        tree.traversal((Integer integer) -> {
            traversalResult.add(integer);
            return false;
        }, BinarySearchTree.TRAVERSAL_MODE_LRD);

        System.out.println();
        System.out.println(traversalResult);

        Printer printer = new InorderPrinter(tree);
        printer.println();

        System.out.println();
        for (int i = 0; i < traversalResult.size() - 1; i++) {
            tree.remove(traversalResult.get(i));
        }
        printer.println();
    }
}
