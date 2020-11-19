package cn.alan.string;

public class StringTest {
    public static void main(String[] args) {
        // 1. 字符串反转
        reverseStringTest();
        // 2. String类的常用方法
        testString();
    }

    /**
     * 字符串反转的方法
     */
    public static void reverseStringTest() {
        // StringBuffer reverse
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("abcdefg");
        System.out.println(stringBuffer.reverse()); // gfedcba
        // StringBuilder reverse
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("abcdefg");
        System.out.println(stringBuilder.reverse()); // gfedcba
    }

    /**
     * String类的常用方法
     */
    private static void testString() {
        String str = "I love China";

        int index = str.indexOf("love");
        System.out.println("indexOf ===>  " + index);

        char ch = str.charAt(0);
        System.out.println("charAt ===> " + ch);

        System.out.println("replace ===> " + str.replace("China", "ZhongGuo"));

        // 第二个参数指定分隔符
        String[] splits = str.split(" ");
        for (String item : splits) {
            System.out.println("split ===> " + item);
        }

        // 下标从0开始，包括beginIndex,不包括endIndex,如果省略endIndex,则取到最后
        System.out.println("substring ===> " + str.substring(2, 6));
    }

}
