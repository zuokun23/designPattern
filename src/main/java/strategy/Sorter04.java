package strategy;

public class Sorter04 {
    public static <T> void sort(T[] arr, Comparator<T> comparator){
        for (int i = 0; i < arr.length - 1 ; i++) {
            int minPos = i;
            for (int j = i + 1 ; j < arr.length; j++) {
                minPos = comparator.compare(arr[j], arr[minPos]) == 1 ? minPos : j;
            }
            swap(arr, i, minPos);
        }
    }

    public static <T> void swap(T[] arr, int i, int j){
        T t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Cat[] cats = {new Cat(3, 3), new Cat(5, 5), new Cat(1, 1)};
        sort(cats, (o1, o2) -> {
            return o1.height < o2.height ? -1 : 1;
        });
        for (Cat cat : cats) {
            System.out.println(cat);
        }

    }
}
