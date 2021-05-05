package strategy;

import java.util.Arrays;

public class Sorter01 {

    public static void sort(int[] arr){
        for (int i = 0; i < arr.length - 1 ; i++) {
            int minPos = i;
            for (int j = i + 1 ; j < arr.length; j++) {
                if(arr[j] < arr[minPos])
                    minPos = j;
            }
            swap(arr, i,minPos);
        }
    }

    public static void swap(int[] arr, int i, int j){
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {9, 2, 3, 5, 7, 1, 4};
        Sorter01 sorter = new Sorter01();
        sorter.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
