package com.newland.boss.export.cdr.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author ${linfengpeng}
 * @Title: MybaisUtil
 * @ProjectName ops-export-cdr
 * @Description: 加密
 * @date 2020/11/3 10:25
 */
public class MybaisUtil {
    private final static  String  key = "pFhd4PG2Sq0=";
    /**
     * 生成秘钥
     * @param
     * @return
     */
    public static String generateDecode() throws UnsupportedEncodingException {
        KeyGenerator keyGen = null;//密钥生成器
        try {
            keyGen = KeyGenerator.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.init(56);//初始化密钥生成器
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        byte[] key = secretKey.getEncoded();//密钥字节数组
        // 进行base64编码
        String encodedKey = Base64.getEncoder().encodeToString(key);
        return encodedKey;
    }

    /**
     * 进行加密
     * @param string
     * @return
     */
    public static String encryptionDecode(String string){
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "DES");//恢复密钥
        Cipher cipher = null;//Cipher完成加密或解密工作类
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//对Cipher初始化，加密模式
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] cipherByte = null;
        try {
            cipherByte = cipher.doFinal(string.getBytes());//加密data
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return Base64.getEncoder().encodeToString(cipherByte);
    }

    /**
     * 进行解密
     * @param string
     * @return
     */
    public static String decryptDecode(String string){
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key), "DES");//恢复密钥
        Cipher cipher = null;//Cipher完成加密或解密工作类
        try {
            cipher = Cipher.getInstance("DES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);//对Cipher初始化，解密模式
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        byte[] cipherByte = new byte[0];//解密data
        try {
            cipherByte = cipher.doFinal(Base64.getDecoder().decode(string));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(cipherByte);
    }
    public static void main(String[] args) throws UnsupportedEncodingException {
        String url="jdbc:oracle:thin:@//10.48.131.66:1521/logdb";
        String username="billlog";
        String password="M0tRR#gb#y2LEr";
        String driver="oracle.jdbc.driver.OracleDriver";
        String s1 = encryptionDecode(url);
        String s2 = encryptionDecode(password);
        String s3 = encryptionDecode(driver);
        String s4 = encryptionDecode(username);
        System.out.println("url:"+s1);
        System.out.println("password:"+s2);
        System.out.println("user:"+s4);
        System.out.println("driver:"+s3);
        String Driver = decryptDecode("oS9KOyRwTpzhs17g3EaS16WDqJ29GvMamng2ZZM/Y0A=");
        System.out.println(Driver);
        String Pw = decryptDecode("UNLIHtyqVe8Brtwo8HG6QQ==");
        System.out.println(Pw);
        String USER = decryptDecode("avBLQuuEchY=");
        System.out.println(USER);
        String URL = decryptDecode("44NUnmUIdwh7ZniYXkV599/WE3o45t91S74ZXV6IeOKcWBGsUnJ0gjq7cArbr61t");
        System.out.println(URL);
    }
}
