package visitor.v2;


import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassWriter;

import java.io.Writer;

/**
 * 光标必须位于类体内，view-show bytecode或者view-show bytecode with jclasslib（安装了jclasslib插件情况下）
 *
 */
public class T1 {
    int i = 0;
    public void m(){
        int j = 1;
    }

}
