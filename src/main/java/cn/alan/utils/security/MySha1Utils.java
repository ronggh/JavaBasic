package cn.alan.utils.security;

import java.security.MessageDigest;

public class MySha1Utils {

    /**
     * 
     * @param s
     * @return
     */
    public static String sha1(String s) {
        if (null == s) {
            return null;
        }

        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(s.getBytes("utf-8"));
            byte messageDigest[] = digest.digest();
            return toHexString(messageDigest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
