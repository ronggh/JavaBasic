package cn.alan.utils.security;

import java.security.MessageDigest;

public class MyShaUtils {

    /**
     * 
     * @param strText
     * @return
     */
    public static String sha1(final String strText) {
        return sha(strText, "SHA-1");
    }

    /**
     * �����ı����ݣ����� SHA-256 ��
     * 
     * @param strText
     * @return
     */
    public static String sha256(final String strText) {
        return sha(strText, "SHA-256");
    }

    /**
     * �����ı����ݣ����� SHA-512 ��
     * 
     * @param strText
     * @return
     */
    public static String sha512(final String strText) {
        return sha(strText, "SHA-512");
    }

    /**
     * �ַ��� SHA ����
     * 
     * @param strSourceText
     * @return
     */
    private static String sha(final String strText, final String strType) {
        // ����ֵ
        String strResult = null;

        // �Ƿ�����Ч�ַ���
        if (strText != null && strText.length() > 0) {
            try {
                // SHA ���ܿ�ʼ
                // �������ܶ��� �������������
                MessageDigest messageDigest = MessageDigest.getInstance(strType);
                // ����Ҫ���ܵ��ַ���

                messageDigest.update(strText.getBytes("utf-8"));

                // �õ� byte ��ͽ��
                byte byteBuffer[] = messageDigest.digest();

                // �� byte �D�Q�� string
                StringBuffer strHexString = new StringBuffer();
                // ��v byte buffer
                for (int i = 0; i < byteBuffer.length; i++) {
                    String hex = Integer.toHexString(0xff & byteBuffer[i]);
                    if (hex.length() == 1) {
                        strHexString.append('0');
                    }
                    strHexString.append(hex);
                }
                // �õ����ؽY��
                strResult = strHexString.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return strResult;
    }

}
