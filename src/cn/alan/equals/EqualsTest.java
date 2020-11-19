package cn.alan.equals;

/**
 * == 和 equals 的区别 对于基本类型和引用类型 == 的作用效果是不同的： 基本类型：比较的是值是否相同； 引用类型：比较的是引用是否相同；
 */
public class EqualsTest {
    public static void main(String[] args) {
        String x = "string";
        String y = "string";
        String z = new String("string");
        System.out.println(x == y); // true
        System.out.println(x == z); // false
        System.out.println(x.equals(y)); // true
        System.out.println(x.equals(z)); // true
    }
}
