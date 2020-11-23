package cn.alan.utils.security;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MyBase64Utils {
    /**
     * 字符集
     */
    private static final char[] LEGALCHARS =
        "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();

    /**
     * 
     * @param text
     * @return
     */
    public static String encode(String text) {
        if (null == text) {
            return null;
        }

        try {
            return encode(text.getBytes("utf-8"));
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * 
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        int start = 0;
        int len = data.length;
        StringBuffer buf = new StringBuffer(data.length * 3 / 2);

        int end = len - 3;
        int i = start;
        int n = 0;

        while (i <= end) {
            int d =
                ((((int)data[i]) & 0x0ff) << 16) | ((((int)data[i + 1]) & 0x0ff) << 8) | (((int)data[i + 2]) & 0x0ff);

            buf.append(LEGALCHARS[(d >> 18) & 63]);
            buf.append(LEGALCHARS[(d >> 12) & 63]);
            buf.append(LEGALCHARS[(d >> 6) & 63]);
            buf.append(LEGALCHARS[d & 63]);

            i += 3;

            if (n++ >= 14) {
                n = 0;
                buf.append(" ");
            }
        }

        if (i == start + len - 2) {
            int d = ((((int)data[i]) & 0x0ff) << 16) | ((((int)data[i + 1]) & 255) << 8);

            buf.append(LEGALCHARS[(d >> 18) & 63]);
            buf.append(LEGALCHARS[(d >> 12) & 63]);
            buf.append(LEGALCHARS[(d >> 6) & 63]);
            buf.append("=");
        } else if (i == start + len - 1) {
            int d = (((int)data[i]) & 0x0ff) << 16;

            buf.append(LEGALCHARS[(d >> 18) & 63]);
            buf.append(LEGALCHARS[(d >> 12) & 63]);
            buf.append("==");
        }

        return buf.toString();
    }

    /**
     * 
     * @param s
     * @return
     */
    public static String decode2String(String s) {
        try {
            String deStr = new String(decode2Bytes(s), "utf-8");
            return deStr;
        } catch (Exception e) {
            return null;
        }

    }

    /**
     * base64解码，返回byte[] 转为String 的方法
     * 
     * @param s
     * @return
     */
    public static byte[] decode2Bytes(String s) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            decode(s, bos);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        byte[] decodedBytes = bos.toByteArray();
        try {
            bos.close();
            bos = null;
        } catch (IOException ex) {
            System.err.println("Error while decoding BASE64: " + ex.toString());
        }
        return decodedBytes;
    }

    /**
     * 私有方法
     * 
     * @param c
     * @return
     */
    private static int decode(char c) {
        if (c >= 'A' && c <= 'Z') {
            return ((int)c) - 65;
        }
        if (c >= 'a' && c <= 'z') {
            return ((int)c) - 97 + 26;
        }
        if (c >= '0' && c <= '9') {
            return ((int)c) - 48 + 26 + 26;
        }

        switch (c) {
            case '+':
                return 62;
            case '/':
                return 63;
            case '=':
                return 0;
            default:
                throw new RuntimeException("unexpected code: " + c);
        }
    }

    //
    private static void decode(String s, OutputStream os) throws IOException {
        int i = 0;

        int len = s.length();

        while (true) {
            while (i < len && s.charAt(i) <= ' ') {
                i++;
            }

            if (i == len) {
                break;
            }

            int tri = (decode(s.charAt(i)) << 18) + (decode(s.charAt(i + 1)) << 12) + (decode(s.charAt(i + 2)) << 6)
                + (decode(s.charAt(i + 3)));

            os.write((tri >> 16) & 255);
            if (s.charAt(i + 2) == '=') {
                break;
            }
            os.write((tri >> 8) & 255);
            if (s.charAt(i + 3) == '=') {
                break;
            }
            os.write(tri & 255);

            i += 4;
        }
    }

}
