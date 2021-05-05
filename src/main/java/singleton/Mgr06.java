package singleton;


import java.util.HashSet;
import java.util.Set;

/**
 *
 * lazy loading
 * 也称懒汉模式
 * 虽然达到了按需初始化的目的，但却带来线程安全性问题
 * 可以通过synchronized解决，但也带来效率下降
 * 妄图通过减小同步代码块的方法提高效率，然后不可行
 * 双重检查
 */
public class Mgr06 {

    private static Mgr06 INSTANCE;

    private Mgr06(){
    }

    public static Mgr06 getInstance(){

        if(INSTANCE == null){
            //双重检查
            synchronized (Mgr06.class) {
                if (INSTANCE == null) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    INSTANCE = new Mgr06();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                set.add(Mgr06.getInstance().hashCode());
            }).start();
        }

        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}
