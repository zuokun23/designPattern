package visitor.v2;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;
import static jdk.internal.org.objectweb.asm.Opcodes.INVOKESTATIC;

public class TestClassWriter1 {

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader(ClassPrinter.class.getClassLoader().getResourceAsStream("visitor/v2/Tank.class"));

        ClassWriter cw = new ClassWriter(0);
        ClassVisitor cv = new ClassVisitor(ASM4, cw) {

            @Override
            public MethodVisitor visitMethod(int i, String s, String s1, String s2, String[] strings) {
                MethodVisitor mv = super.visitMethod(i, s, s1, s2, strings);
                return new MethodVisitor(ASM4, mv) {
                    @Override
                    public void visitCode() {
                        visitMethodInsn(INVOKESTATIC, "TimeProxy", "before", "()V", false);
                        super.visitCode();
                    }
                };
            }
        };
        cr.accept(cv, 0);
        byte[] b2 = cw.toByteArray();

        String path = (String)System.getProperties().get("user.dir");
        File f = new File(path + "/src/main/java/visitor/v2/ASM/");
        f.mkdirs();

        //将byte[]数组写入Tank_0.class
        FileOutputStream fos = new FileOutputStream(new File(path + "/src/main/java/visitor/v2/ASM/Tank_0.class"));
        fos.write(b2);
        fos.flush();
        fos.close();
    }
}

class Tank{
    public void move(){
        System.out.println("Tank Moving ClaClaCla ...");
    }
}

class TankProxy {
    public static void before(){
        System.out.println("before ...");
    }
}
