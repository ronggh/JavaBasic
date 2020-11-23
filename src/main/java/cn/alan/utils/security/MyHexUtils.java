package cn.alan.utils.security;

public class MyHexUtils {
    private final static String HEX = "0123456789abcdef";

    /**
     * 
     * @param text
     * @return
     */
    public static String toHex(String text) {

        try {
            byte[] buf = text.getBytes("utf-8");
            return toHex(buf);
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 
     * @param data
     * @return
     */
    public static String toHex(byte[] data) {
        if (data == null)
            return "";
        StringBuffer result = new StringBuffer(2 * data.length);
        for (int i = 0; i < data.length; i++) {
            appendHex(result, data[i]);
        }
        return result.toString();
    }

    /**
     * 
     * @param strHex
     * @return
     */
    public static String hex2String(String strHex) {
        try {
            byte[] buf = hex2Bytes(strHex);
            return new String(buf, "utf-8");
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 
     * @param hexString
     * @return
     */
    public static byte[] hex2Bytes(String hexString) {
        int len = hexString.length() / 2;
        byte[] result = new byte[len];
        for (int i = 0; i < len; i++)
            result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
        return result;
    }

    //
    private static void appendHex(StringBuffer sb, byte b) {
        sb.append(HEX.charAt((b >> 4) & 0x0f)).append(HEX.charAt(b & 0x0f));
    }
}
