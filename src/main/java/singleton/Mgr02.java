package singleton;

/**
 * 和第一种本质一样，初始化放入了static块中
 */
public class Mgr02 {

    //private static final
    private static final Mgr02 INSTANCE;
    static{
        INSTANCE = new Mgr02();
    }
    //禁止类外面实例化
    private Mgr02() {
    }

    public static Mgr02 getINSTANCE() {
        return INSTANCE;
    }

    public static void main(String[] args) {
        Mgr02 mgr01 = Mgr02.getINSTANCE();
        Mgr02 mgr02 = Mgr02.getINSTANCE();
        System.out.println(mgr01 == mgr02);

    }
}
//true
