package cn.alan;

import cn.alan.utils.MyStringUtils;

public class MyStringUtilsTest {
    public static void main(String[] args) {
        String str = "1234";
        System.out.println(str + " isNum ===> " + MyStringUtils.isNum(str));
        str = "12";
        System.out.println(str + " isNumeric ===> " + MyStringUtils.isNumeric(str));
        str = "12.34";
        System.out.println(str + " isDecimal ===> " + MyStringUtils.isDecimal(str));
        str = "\r\n";
        System.out.println(str + " isBlank ===> " + MyStringUtils.isBlank(str));
        System.out.println(str + " isEmpty ===> " + MyStringUtils.isEmpty(str));
        str = "中国";
        System.out.println(str + " isChineseString ===> " + MyStringUtils.isChineseString(str));

        str = "abc";
        System.out.println(str + " isChineseString ===> " + MyStringUtils.isChineseString(str));
    }
}
