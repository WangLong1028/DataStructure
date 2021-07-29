package com.wl;

import com.wl.array.DynamicArray;

public class Main {
    public static void main(String[] argv){
        testDynamicArray();
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
}
