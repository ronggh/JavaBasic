package cn.alan.utils;

import java.io.*;

public class CloneUtils {
    public static <T extends Serializable> T clone(T obj) throws Exception {
        // 把obj写到输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);

        // 从流中克隆出一个新对象返回
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (T)ois.readObject();
    }
}
