package cn.alan.utils.security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// DES加密
public class MyDesUtils {
    //
    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};

    /***
     * DES加密砿
     *
     * @param text
     * @param key,
     *            密钥，长度为8个字符，不足的补0，多余的会截掿
     * @return
     * @throws Exception
     */
    public static String encrypt(String text, String key) {
        if (null == text || null == key) {
            return null;
        }

        int keyLength = key.length();
        String encryptKey = key;
        if (keyLength < 8) {
            for (int i = keyLength; i < 8; i++) {
                encryptKey += "0";
            }
        } else if (keyLength > 8) {
            encryptKey = key.substring(0, 8);
        }

        try {
            IvParameterSpec zeroIv = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(encryptKey.getBytes("utf-8"), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, zeroIv);
            byte[] encryptedData = cipher.doFinal(text.getBytes("utf-8"));

            return MyBase64Utils.encode(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * DES解密
     *
     * @param text
     * @param key,
     *            长度丿个字符，不足的补0，多余的会截掿
     * @return
     * @throws Exception
     */
    public static String decrypt(String text, String key) {
        if (null == text || null == key) {
            return null;
        }

        int keyLength = key.length();
        String decryptKey = key;
        if (keyLength < 8) {
            for (int i = keyLength; i < 8; i++) {
                decryptKey += "0";
            }
        } else if (keyLength > 8) {
            decryptKey = key.substring(0, 8);
        }

        try {
            byte[] byteMi = MyBase64Utils.decode2Bytes(text);
            IvParameterSpec zeroIv = new IvParameterSpec(iv);

            SecretKeySpec keySpec = new SecretKeySpec(decryptKey.getBytes("utf-8"), "DES");
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, zeroIv);
            byte decryptedData[] = cipher.doFinal(byteMi);

            return new String(decryptedData, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
