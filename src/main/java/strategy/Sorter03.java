package strategy;

public class Sorter03 {

    /**
     * 想要对任何类型进行排序，本来可以使用Object[]，但是Object类没有实现compareTo()函数
     * 可以使用Comparable接口
     * @param arr
     */
    public static void sort(Comparable[] arr){
        for (int i = 0; i < arr.length - 1 ; i++) {
            int minPos = i;
            for (int j = i + 1 ; j < arr.length; j++) {
                minPos = arr[j].compareTo(arr[minPos]) == -1 ? j : minPos;
            }
            swap(arr, i,minPos);
        }
    }

    public static void swap(Comparable[] arr, int i, int j){
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        Cat[] cats = {new Cat(3, 3), new Cat(5, 5), new Cat(1, 1)};
        sort(cats);
        for (Cat cat : cats) {
            System.out.println(cat);
        }

        Dog[] dogs = {new Dog(5), new Dog(2), new Dog(3), new Dog(-2)};
        sort(dogs);
        for (Dog dog : dogs) {
            System.out.println(dog);
        }
    }
}
