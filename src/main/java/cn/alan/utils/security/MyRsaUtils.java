package cn.alan.utils.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.Cipher;

public class MyRsaUtils {

    /**
     *
     * 用公匙加密,加密后的密文再进行Base64编码
     * 
     * @param src
     * @return
     */
    public static String encodeWithPublicKey(String publicKeyString, String src) {
        //
        if (null == publicKeyString || null == src) {
            return null;
        }

        // 加载公匙
        RSAPublicKey publicKey = MyRsaGenKeyUtils.loadPublicKeyFromBase64String(publicKeyString);

        //
        if (publicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                byte[] srcBytes = src.getBytes("utf-8");
                String result = MyBase64Utils.encode(cipher.doFinal(srcBytes));
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     *
     * 用私匙解密，密文是Base64编码过的
     * 
     * @param
     * @return
     */
    public static String decodeWithPrivateKey(String privateKeyString, String des) {
        if (null == privateKeyString || null == des) {
            return null;
        }

        //
        RSAPrivateKey privateKey = MyRsaGenKeyUtils.loadPrivateKeyFromBase64String(privateKeyString);

        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] encBytes = MyBase64Utils.decode2Bytes(des);
                String result = new String(cipher.doFinal(encBytes), "utf-8");
                return result;

            } catch (Exception e) {
                System.out.println("exception in decodeWithPrivateKey " + e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     *
     * 用私匙加密,加密后的密文再进行Base64编码
     * 
     * @param src
     * @return
     */
    public static String encodeWithPrivateKey(String privateKeyString, String src) {
        //
        if (null == privateKeyString || null == src) {
            return null;
        }

        // 加载私匙
        RSAPrivateKey privateKey = MyRsaGenKeyUtils.loadPrivateKeyFromBase64String(privateKeyString);

        //
        if (privateKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                byte[] srcBytes = src.getBytes("utf-8");
                String result = MyBase64Utils.encode(cipher.doFinal(srcBytes));
                return result;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     *
     * 用公匙解密，密文是Base64编码过的
     * 
     * @param
     * @return
     */
    public static String decodeWithPublicKey(String publicKeyString, String des) {
        if (null == publicKeyString || null == des) {
            return null;
        }

        //
        RSAPublicKey publicKey = MyRsaGenKeyUtils.loadPublicKeyFromBase64String(publicKeyString);

        if (publicKey != null) {
            try {
                Cipher cipher = Cipher.getInstance("RSA");
                cipher.init(Cipher.DECRYPT_MODE, publicKey);
                byte[] encBytes = MyBase64Utils.decode2Bytes(des);
                String result = new String(cipher.doFinal(encBytes), "utf-8");
                return result;

            } catch (Exception e) {
                System.out.println("exception in decodeWithPublicKey " + e.getMessage());
                e.printStackTrace();
            }
        }

        return null;
    }
}