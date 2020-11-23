package cn.alan.utils.security;

public class MyGuidUtils {

    /***
     * 产生GUID,中间无分隔线“-”
     * 
     * @return
     */
    public static String generateGuid() {
        String str = generateGuidWithSeparator();
        return str.replaceAll("-", "");
    }

    /***
     * 产生GUID，中间有分隔线 -
     */
    public static String generateGuidWithSeparator() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     *
     * @return
     */
    public static String generateGuidWithoutSeparator() {
        return generateGuid();
    }

}