package cn.alan;

import cn.alan.utils.MyStringUtils;

public class MyStringUtilsTest {
    public static void main(String[] args) {
        System.out.println("isNum ===> " + MyStringUtils.isNum("1234"));
        System.out.println("isNumeric ===> " + MyStringUtils.isNumeric("12"));
        System.out.println("isDecimal ===> " + MyStringUtils.isDecimal("12.34"));
    }
}
