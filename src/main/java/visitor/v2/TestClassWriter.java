package visitor.v2;

import jdk.internal.org.objectweb.asm.ClassWriter;

import static jdk.internal.org.objectweb.asm.Opcodes.*;

/**
 * 动态生成下面接口的字节码
 * package pkg;
 * public interface Comparable extends Mesurable {
 *     int LESS = -1;
 *     int EQUAL = 0;
 *     int GREATER = 1;
 *     int compareTo(Object o);
 * }
 */
public class TestClassWriter {

    public static void main(String[] args) {
        ClassWriter cw = new ClassWriter(0);
        cw.visit(V1_5, ACC_PUBLIC + ACC_ABSTRACT + ACC_INTERFACE,
                "pkg/Comparable", null, "java/lang/Object",
                null);//new String[] { "visitor/v2/Mesurable" });
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "LESS", "I",
                null, new Integer(-1)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "EQUAL", "I",
                null, new Integer(0)).visitEnd();
        cw.visitField(ACC_PUBLIC + ACC_FINAL + ACC_STATIC, "GREATER", "I",
                null, new Integer(1)).visitEnd();
        cw.visitMethod(ACC_PUBLIC + ACC_ABSTRACT, "compareTo",
                "(Ljava/lang/Object;)I", null, null).visitEnd();
        cw.visitEnd();
        byte[] b = cw.toByteArray();

        Class clazz = new MyClassLoader().defineClass("pkg.Comparable", b);
        System.out.println(clazz.getMethods()[0].getName());

    }

}

class MyClassLoader extends ClassLoader {
    public Class defineClass(String name, byte[] b) {
        return defineClass(name, b, 0, b.length);
    }
}

class Mesurable{

}