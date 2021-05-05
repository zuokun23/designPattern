package singleton;

import java.util.HashSet;
import java.util.Set;

/**
 * lazy loading
 * 也称懒汉模式
 * 虽然达到了按需初始化的目的，但却带来线程安全性问题
 * 可以通过synchronized解决，但也带来效率下降
 */
public class Mgr04 {

    private static Mgr04 INSTANCE;

    private Mgr04(){
    }

    public static synchronized Mgr04 getInstance(){

        if(INSTANCE == null){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Mgr04();
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() ->{
                set.add(Mgr04.getInstance().hashCode());
            }).start();
        }

        for (Integer integer : set) {
            System.out.println(integer);
        }
    }
}
