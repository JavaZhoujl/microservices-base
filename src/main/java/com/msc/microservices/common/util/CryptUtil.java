package com.msc.microservices.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * 加解密工具类
 *
 * @author zjl
 */
public final class CryptUtil {
    private static final String AES = "AES";
    private static final String DES = "DES";

    /**
     * AES加密
     *
     * @param content 源字符串
     * @param aesKey  秘钥
     * @return 加密后字节数组
     */
    public static byte[] AESEncrypt(String content, String aesKey) {
        return AES(Cipher.ENCRYPT_MODE, content.getBytes(), aesKey);
    }

    /**
     * AES解密
     *
     * @param content 加密后的字符串
     * @param aesKey  秘钥
     * @return 源字节数组
     */
    public static byte[] AESDecrypt(byte[] content, String aesKey) {
        return AES(Cipher.DECRYPT_MODE, content, aesKey);
    }

    private static byte[] AES(int mode, byte[] content, String aesKey) {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(256, new SecureRandom(aesKey.getBytes()));
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] encodeSecretKey = secretKey.getEncoded();
            SecretKeySpec keySpec = new SecretKeySpec(encodeSecretKey, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException
                | InvalidKeyException | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * DES加密
     *
     * @param content 源字符串
     * @param desKey  秘钥
     * @return 加密后字符串
     */
    public static byte[] DESEncrypt(String content, String desKey) {
        return DES(Cipher.ENCRYPT_MODE, content.getBytes(), desKey);
    }

    /**
     * DES解码
     *
     * @param content 源字符串
     * @param desKey  秘钥
     * @return 解码后字符串
     */
    public static byte[] DESDecrypt(byte[] content, String desKey) {
        return DES(Cipher.DECRYPT_MODE, content, desKey);
    }

    private static byte[] DES(int mode, byte[] content, String desKey) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(desKey.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
            SecretKey sk = keyFactory.generateSecret(dks);
            Cipher cipher = Cipher.getInstance(DES);
            cipher.init(mode, sk, sr);
            return cipher.doFinal(content);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes 字节数组
     * @return 16进制字符串, 默认小写
     */
    public static String byte2Hex(byte[] bytes) {
        return byte2Hex(bytes, true);
    }

    /**
     * 字节数组转16进制字符串
     *
     * @param bytes       字节数组
     * @param toLowerCase 是否小写
     * @return 16进制字符串
     */
    public static String byte2Hex(byte[] bytes, boolean toLowerCase) {
        return new String(Hex.encodeHex(bytes, toLowerCase));
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex 16进制字符串
     * @return 字节数组
     */
    public static byte[] hex2Byte(String hex) {
        try {
            return Hex.decodeHex(hex.toCharArray());
        } catch (DecoderException e) {
            return null;
        }
    }

    /**
     * base64加密
     *
     * @param encode 源字符串
     * @return 加密后字符串
     */
    public static byte[] base64Encode(String encode) {
        return Base64.getEncoder().encode(encode.getBytes());
    }

    /**
     * base64加密
     *
     * @param encode 源字符串
     * @return 加密后字符串
     */
    public static byte[] base64Encode(byte[] encode) {
        return Base64.getEncoder().encode(encode);
    }

    /**
     * base64解密
     *
     * @param decode 源字符串
     * @return 解密后字符串
     */
    public static byte[] base64Decode(String decode) {
        return base64Decode(decode.getBytes());
    }

    /**
     * base64解密
     *
     * @param decode 源字符串
     * @return 解密后字符串
     */
    public static byte[] base64Decode(byte[] decode) {
        return Base64.getDecoder().decode(decode);
    }

    public static byte[] md5(byte[] content) {
        return md5(content);
    }

    public static byte[] md5(String content) {
        return md5(content);
    }

    public static String md52Hex(byte[] content) {
        return md5Hex(content);
    }

    public static String md52Hex(String content) {
        return md5Hex(content);
    }

    public static byte[] sha256(byte[] content) {
        return sha256(content);
    }

    public static byte[] sha256(String content) {
        return sha256(content);
    }

    public static String sha2562Hex(byte[] content) {
        return sha256Hex(content);
    }

    public static String sha2562Hex(String content) {
        return sha256Hex(content);
    }
}
