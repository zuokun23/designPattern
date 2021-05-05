package singleton;

/**
 * 不仅可以解决线程同步，而且可以防止反序列化（反序列化不成枚举）
 */
public enum Mgr08 {
    INSTANCE;

    //此处写业务方法
    public void m(){
        System.out.println("m..");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            System.out.println(Mgr08.INSTANCE.hashCode());
        }
    }
}
