package com.rest.restapi.util;


import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES：密码学中的高级加密标准（Advanced Encryption Standard，AES），
 * 又称Rijndael加密法，是美国联邦政府采用的一种区块加密标准。
 * 这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。是一种对称加密算法。
 */
public class AesUtils {

    private final static Logger logger = LoggerFactory.getLogger(AesUtils.class);

    /**
     * AES 加密
     *
     * @param sSrc，需要加密的字符
     * @param sKey,加密密钥
     * @return
     * @throws Exception
     */
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            logger.info("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            logger.info("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes("utf-8");
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");//"算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("utf-8"));

        return new Base64().encodeToString(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

    /**
     * AES 解密
     *
     * @param sSrc，需要加密的字符
     * @param sKey,加密密钥
     * @return
     * @throws Exception
     */
    public static String Decrypt(String sSrc, String sKey) throws Exception {
        try {
            // 判断Key是否正确
            if (sKey == null) {
                logger.info("Key为空null");
                return null;
            }
            // 判断Key是否为16位
            if (sKey.length() != 16) {
                logger.info("Key长度不是16位");
                return null;
            }
            byte[] raw = sKey.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            byte[] encrypted1 = new Base64().decode(sSrc);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original, "utf-8");
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
            return null;
        }
    }

}
