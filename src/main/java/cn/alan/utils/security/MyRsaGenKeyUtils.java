package cn.alan.utils.security;

import java.io.*;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class MyRsaGenKeyUtils {

    /**
     * 随机生成一对RSA秘钥
     *
     * @return
     */

    public static KeyPair genKeyPair() {
        //
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
            keyPairGen.initialize(1024, new SecureRandom());
            keyPair = keyPairGen.generateKeyPair();

            /*
             * RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
             * RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
             *
             *
             * System.out.println("Public Key = " +
             * keyBytes2Base64String(publicKey.getEncoded()));
             * System.out.println("Private Key = " +
             * keyBytes2Base64String(privateKey.getEncoded()));
             */

        } catch (Exception e) {
            e.printStackTrace();
        }

        //

        return keyPair;
    }

    /**
     *
     * @param publicKeyFileName
     * @param privateKeyFileName
     */
    public static boolean writeKeyToFile(String publicKeyFileName, String privateKeyFileName) {
        if (null == publicKeyFileName || null == privateKeyFileName) {
            return false;
        }

        KeyPair keyPair = genKeyPair();

        //
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = MyBase64Utils.encode(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = MyBase64Utils.encode(privateKey.getEncoded());
            // 将密钥对写入到文件
            FileWriter pubfw = new FileWriter(privateKeyFileName);
            FileWriter prifw = new FileWriter(publicKeyFileName);

            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 将生成的秘钥对写入一对文件中，分别为publicKey.keystore和privateKey.keystore， 秘钥对是进行过Base64编码后的字符串
     *
     * @param filePath
     */
    public static boolean writeKeyPairToFile(String filePath) {
        if (null == filePath) {
            return false;
        }

        KeyPair keyPair = genKeyPair();

        //
        // 得到私钥
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        // 得到公钥
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        try {
            // 得到公钥字符串
            String publicKeyString = MyBase64Utils.encode(publicKey.getEncoded());
            // 得到私钥字符串
            String privateKeyString = MyBase64Utils.encode(privateKey.getEncoded());
            // 将密钥对写入到文件
            FileWriter pubfw = new FileWriter(filePath + "/publicKey.keystore");
            FileWriter prifw = new FileWriter(filePath + "/privateKey.keystore");
            BufferedWriter pubbw = new BufferedWriter(pubfw);
            BufferedWriter pribw = new BufferedWriter(prifw);
            pubbw.write(publicKeyString);
            pribw.write(privateKeyString);
            pubbw.flush();
            pubbw.close();
            pubfw.close();
            pribw.flush();
            pribw.close();
            prifw.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;

    }

    /**
     *
     * @param bytes
     * @return
     */
    protected static String keyBytes2Base64String(byte[] bytes) {
        return MyBase64Utils.encode(bytes);
    }

    /**
     * 从Base64编码字符串中生成公匙
     *
     * @param publicKeyString
     * @return
     */
    public static RSAPublicKey loadPublicKeyFromBase64String(String publicKeyString) {
        if (publicKeyString == null || publicKeyString.isEmpty()) {
            return null;
        }

        try {
            byte[] bytes = MyBase64Utils.decode2Bytes(publicKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
            RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     * 从Base64编码字符串中生成私匙
     *
     * @param privateKeyString
     * @return
     */
    public static RSAPrivateKey loadPrivateKeyFromBase64String(String privateKeyString) {
        if (privateKeyString == null || privateKeyString.isEmpty()) {
            return null;
        }

        try {
            byte[] bytes = MyBase64Utils.decode2Bytes(privateKeyString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            RSAPrivateKey privateKey = (RSAPrivateKey)keyFactory.generatePrivate(keySpec);
            return privateKey;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    /**
     *
     * @param path
     * @param fileName
     * @return
     */
    public static String loadPublicKeyFromFile(String path, String fileName) {
        //
        if (null == path || null == fileName) {
            return null;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(path + File.separator + fileName));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从指定的路径中加载公匙文件，生成Base64编码的公匙字串
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static String loadPublicKeyFromFile(String path) {

        return loadPublicKeyFromFile(path, "publicKey.keystore");

    }

    /**
     *
     * @param path
     * @param fileName
     * @return
     */
    public static String loadPrivateKeyFromFile(String path, String fileName) {
        //
        if (null == path || null == fileName) {
            return null;
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(path + File.separator + fileName));
            String readLine = null;
            StringBuilder sb = new StringBuilder();
            while ((readLine = br.readLine()) != null) {
                sb.append(readLine);
            }
            br.close();
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 默认文件名为：privateKey.keystore
     *
     * @param path
     * @return
     */
    public static String loadPrivateKeyFromFile(String path) {

        return loadPrivateKeyFromFile(path, "privateKey.keystore");
    }

}
