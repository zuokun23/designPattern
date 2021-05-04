package singleton;

/**
 * 饿汉式
 * 类加载到内存后，就实例化一个单例，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类加载就完成实例化
 * （话说你不用的，你加载干啥）
 */
public class Mgr01 {

    //private static final
    private static final Mgr01 INSTANCE = new Mgr01();
    //禁止类外面实例化
    private Mgr01() {
    }

    public static Mgr01 getINSTANCE() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr01 mgr01 = Mgr01.getINSTANCE();
        Mgr01 mgr02 = Mgr01.getINSTANCE();
        System.out.println(mgr01 == mgr02);

    }
}
//true
