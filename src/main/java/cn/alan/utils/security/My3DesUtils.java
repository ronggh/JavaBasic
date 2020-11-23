package cn.alan.utils.security;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

public class My3DesUtils {

    // 向量
    private final static String iv = "01234567";
    // 加解密统一使用的编码方式
    private final static String encoding = "utf-8";

    /**
     * 3DES加密 *
     * 
     * @param text
     *            普通文本
     * @param key
     *            密钥,长度为24个字符
     * @return
     * @throws Exception
     */
    public static String encrypt(String text, String key) {
        if (null == text || null == key) {
            return null;
        }
        int keyLength = key.length();
        String secretKey = key;
        if (keyLength < 24) {
            for (int i = keyLength; i < 24; i++) {
                secretKey += "0";
            }
        }

        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes(encoding));
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);

            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
            byte[] encryptData = cipher.doFinal(text.getBytes(encoding));
            return MyBase64Utils.encode(encryptData);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES解密 *
     * 
     * @param text
     *            加密文本
     * @param key
     *            密钥，长度不小于24个字符
     * @return
     * @throws Exception
     */
    public static String decrypt(String text, String key) {
        if (null == text || null == key) {
            return null;
        }

        int keyLength = key.length();
        String secretKey = key;
        if (keyLength < 24) {
            for (int i = keyLength; i < 24; i++) {
                secretKey += "0";
            }
        }

        try {
            Key deskey = null;
            DESedeKeySpec spec = new DESedeKeySpec(secretKey.getBytes(encoding));
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
            deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(iv.getBytes(encoding));
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips);

            byte[] decryptData = cipher.doFinal(MyBase64Utils.decode2Bytes(text));

            return new String(decryptData, encoding);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}