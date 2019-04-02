package com.moon.studentplatform.util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static com.moon.studentplatform.util.HexUtils.bytesToHexString;
import static com.moon.studentplatform.util.HexUtils.hexStringToBytes;

/**
 * RSA加解密工具
 *
 * @author Moon
 */
public class RsaUtils {
    /**
     * 生成密钥对
     *
     * @param keyLength 密钥长度
     * @return 返回密钥对
     * @throws Exception 抛出
     */
    public static KeyPair genKeyPair(int keyLength) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(keyLength);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * @param content   要加密的数据
     * @param publicKey 公钥加密
     * @return 返回加密后的数据
     * @throws Exception 抛出
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey) throws Exception {
        //java默认"RSA"="RSA/ECB/PKCS1Padding"
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(content);
    }

    /**
     * @param content    要解密的数据
     * @param privateKey 私钥解密
     * @return 返回解密后的数据
     * @throws Exception 抛出
     */
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(content);
    }

    /**
     * @param publicKey Base64加密的公钥字符串
     * @return 返回公钥对象
     * @throws Exception 抛出
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * @param privateKey Base64加密的私钥字符串
     * @return 返回私钥对象
     * @throws Exception 抛出
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * @param modulusStr  公钥n
     * @param exponentStr 公钥e
     * @return 公钥对象
     * @throws Exception 抛出
     */
    public static PublicKey getPublicKey(String modulusStr, String exponentStr) throws Exception {
        BigInteger modulus = new BigInteger(modulusStr);
        BigInteger exponent = new BigInteger(exponentStr);
        RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(publicKeySpec);
    }

    /**
     * @param modulusStr  私钥n
     * @param exponentStr 私钥d
     * @return 私钥对象
     * @throws Exception 抛出
     */
    public static PrivateKey getPrivateKey(String modulusStr, String exponentStr) throws Exception {
        BigInteger modulus = new BigInteger(modulusStr);
        BigInteger exponent = new BigInteger(exponentStr);
        RSAPrivateKeySpec privateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(privateKeySpec);
    }

    /**
     * RSA公钥加密长数据
     *
     * @param content   等待加密的数据
     * @param publicKey RSA 公钥
     * @return 加密后的密文(16进制的字符串)
     */
    public static String longDataEncrypt(String content, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return doFinal(content.getBytes(), cipher, publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA私钥解密长数据
     *
     * @param content    等待解密的数据
     * @param privateKey RSA 私钥
     * @return 解密后的明文
     */
    public static String longDataDecrypt(String content, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] contentBytes = hexStringToBytes(content);
            return doFinal(contentBytes, cipher, privateKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    private static String doFinal(byte[] content, Cipher cipher, Key key) throws IllegalBlockSizeException, BadPaddingException {
        //该密钥能够加密的最大字节长度
        int splitLength = ((RSAKey) key).getModulus().bitLength() / 8;
        if (key instanceof PublicKey)
            splitLength -= 11;
        byte[][] arrays = splitBytes(content, splitLength);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte[] array : arrays) {
            if (key instanceof PublicKey)
                stringBuilder.append(bytesToHexString(cipher.doFinal(array)));
            else
                stringBuilder.append(new String(cipher.doFinal(array)));
        }
        return stringBuilder.toString();
    }

    private static byte[][] splitBytes(byte[] bytes, int blockSize) {
        int quotient = bytes.length / blockSize;
        int remainder = bytes.length % blockSize;
        if (remainder != 0)
            quotient++;
        byte[][] arrays = new byte[quotient][];
        byte[] arr;
        for (int i = 0, size = blockSize; i < quotient; i++) {
            if (i == quotient - 1 && remainder != 0) {
                size = remainder;
            }
            arr = new byte[size];
            System.arraycopy(bytes, i * blockSize, arr, 0, size);
            arrays[i] = arr;
        }
        return arrays;
    }
}