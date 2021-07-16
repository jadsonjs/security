/*
 * Cryptography
 * AESCryptography
 * @since 16/07/2021
 */
package br.com.jadson.cryptography.symmetric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public final class AESCryptography {

    public static final String AES = "AES/ECB/PKCS5Padding";

    private static AESCryptography aes;

    private AESCryptography(){ }

    public static AESCryptography getInstance(){
        if(aes == null ){
            aes = new AESCryptography();
        }
        return aes;
    }

    /**
     * Encrypt the message using AES algorithm
     *
     * @param message
     * @param key
     * @return
     */
    public byte[] encrypt(String message, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, convertToKey(key));
            byte[] cipherText = cipher.doFinal(message.getBytes("UTF-8"));
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Decrypt the message using AES algorithm
     *
     * @param cipherText
     * @param key
     * @return
     */
    public String decrypt(byte[] cipherText, String key) {
        try {
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, convertToKey(key));
            return new String(cipher.doFinal( cipherText ) );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public String generateKey() {
        try {
            SecureRandom random = new SecureRandom();
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(256, random);
            SecretKey secretKey = kgen.generateKey();
            return convertToText(secretKey);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String convertToText(SecretKey secretKey) {
        return new String(Base64.getEncoder().encode(secretKey.getEncoded()));
    }


    private SecretKey convertToKey(String secretKey) {
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

}
