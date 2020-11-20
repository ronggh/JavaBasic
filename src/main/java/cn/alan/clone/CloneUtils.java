package cn.alan.clone;

import java.io.*;

public class CloneUtils {
    public static <T extends Serializable> T clone(T obj) throws Exception {
        // 说明：调用ByteArrayInputStream或ByteArrayOutputStream对象的close方法没
        // 这两个基于内存的流只要垃圾回收器清理对象就能够释放资源，这一点不同于对外部资
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T)ois.readObject();
    }
}
