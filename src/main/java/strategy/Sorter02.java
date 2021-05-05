package strategy;

public class Sorter02 {

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

    public static void sort(Cat[] arr){
        for (int i = 0; i < arr.length - 1 ; i++) {
            int minPos = i;
            for (int j = i + 1 ; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr, i,minPos);
        }
    }

    public static void swap(Cat[] arr, int i, int j){
        Cat t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Cat[] cats = {new Cat(3, 3), new Cat(5, 5), new Cat(1, 1)};
        sort(cats);
        for (Cat cat : cats) {
            System.out.println(cat);
        }
    }
}
