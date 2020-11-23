package cn.alan.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyMd5Utils {
    /**
     * 产生32位MD5编码
     *
     * @params text
     * @return
     */
    public static String md5(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(text.getBytes());
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * MD5 较易破解，可以进行若干次迭代，以增加破解的难度
     *
     * @param text
     * @param repeatCount
     * @return
     */
    public static String md5(String text, int repeatCount) {
        if (repeatCount <= 1)
            return md5(text);
        else {
            String oldStr = text;
            String newStr = null;

            for (int i = 1; i <= repeatCount; i++) {
                newStr = md5(oldStr);
                oldStr = newStr;
            }
            return newStr;
        }
    }

    //
    private static String toHexString(byte[] keyData) {
        if (keyData == null) {
            return null;
        }

        int expectedStringLen = keyData.length * 2;
        StringBuilder sb = new StringBuilder(expectedStringLen);

        for (int i = 0; i < keyData.length; i++) {
            String hexStr = Integer.toString(keyData[i] & 0x00FF, 16);
            if (hexStr.length() == 1) {
                hexStr = "0" + hexStr;
            }
            sb.append(hexStr);
        }
        return sb.toString();
    }
}
