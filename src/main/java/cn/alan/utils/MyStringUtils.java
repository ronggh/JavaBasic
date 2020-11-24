package cn.alan.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtils {
    private final static String EMPTY_SPACE = "";
    private final static String CHINESE_CHARS =
        "[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]";

    /**
     * 是否为空，包括null和""
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return (null == str || EMPTY_SPACE.equals(str));
    }

    /**
     * checkValue为 null 或者为 "" 时返回 defaultValue
     * 
     * @param checkValue
     * @param defaultValue
     * @return
     */
    public static String isEmpty(String checkValue, String defaultValue) {
        return isEmpty(checkValue) ? defaultValue : checkValue;
    }

    /**
     * 是否为空或空白字符，如" " ,"\r"等 空白字符<br/>
     * （包括空格，制表符\t，换行符\n，换页符\f，回车\r）
     * 
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (isEmpty(str)) {
            return true;
        }

        if (str.length() == 0) {
            return true;
        }
        //
        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 判断字符串长度是否合法
     * 
     * @param instr
     *            输入字符串
     * @param minLength
     *            最小长度
     * @param maxLength
     *            最大长度
     * @return true = 合法
     */
    public static boolean chackStringLength(String instr, int minLength, int maxLength) {
        return !(instr.length() < minLength || instr.length() > maxLength);
    }

    /**
     * 判断字符串是否全是数字
     *
     * @param str
     *            输入字符串
     * @return true=全是数字
     */
    public static boolean isNum(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("\\d+");
    }

    /**
     * 判断是否为整数
     *
     * @param s
     * @return
     */
    public static boolean isNumeric(String s) {
        if ((s != null) && (s != "")) {
            return s.matches("^[0-9]*$");
        }

        return false;
    }

    /**
     * 判断字符串是否为小数
     * 
     * @param str
     * @return
     */
    public static boolean isDecimal(String str) {
        String reg = "^[0-9]+(.[0-9]+)?$";
        return str.matches(reg);
    }

    /**
     * 判断字符串是否由字母和数字组成
     *
     * @param str
     *            输入字符串
     * @return
     */
    public static boolean isNumOrLetter(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("^[0-9a-zA-Z]+$");
    }

    /**
     * 首字母变小写
     * 
     * @param str
     * @return
     */
    public static String firstCharToLowerCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'A' && firstChar <= 'Z') {
            char[] arr = str.toCharArray();
            arr[0] += ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 首字母变大写
     * 
     * @param str
     * @return
     */
    public static String firstCharToUpperCase(String str) {
        char firstChar = str.charAt(0);
        if (firstChar >= 'a' && firstChar <= 'z') {
            char[] arr = str.toCharArray();
            arr[0] -= ('a' - 'A');
            return new String(arr);
        }
        return str;
    }

    /**
     * 判断是否为中文
     * 
     * @param str
     * @return
     */
    public static boolean isChineseString(String str) {
        return str.matches("[\u4E00-\u9FA5]+");
    }

    /**
     * 字符串是否包含中文
     *
     * @param str
     *            待校验字符串
     * @return true 包含中文字符 false 不包含中文字符
     * 
     */
    public static boolean isContainChinese(String str) {

        if (isEmpty(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile(CHINESE_CHARS);
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }
}
