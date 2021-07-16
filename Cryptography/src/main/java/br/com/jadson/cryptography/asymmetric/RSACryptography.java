/*
 * Cryptography
 * RSACryptography
 * @since 16/07/2021
 */
package br.com.jadson.cryptography.asymmetric;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public final class RSACryptography {

    public static final String RSA = "RSA";

    public static final int KEY_SIZE = 1024;

    private static RSACryptography rsa;

    private RSACryptography(){ }

    public static RSACryptography getInstance(){
        if(rsa == null ){
            rsa = new RSACryptography();
        }
        return rsa;
    }

    public byte[] encrypt(String message, String publicKey) {
        byte[] cipherText = null;
        try {
            final Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, convertToPublicKey(publicKey));
            cipherText = cipher.doFinal(message.getBytes());
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(byte[] cipherText, String privateKey) {
        byte[] dectyptedText = null;
        try {

            final Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, convertToPrivateKey(privateKey));
            dectyptedText = cipher.doFinal(cipherText);
            String plainText = new String(dectyptedText);
            return plainText;
        } catch (Exception ex) {
            return null;
        }

    }



    public String[] generanteKeys() {
        try {
            SecureRandom random = new SecureRandom();
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(RSA);
            keyGen.initialize(KEY_SIZE, random);
            final KeyPair keyPair = keyGen.generateKeyPair();
            return new String[]{ convertPublicKeyAsText(keyPair.getPublic()), convertPrivateKeyAsText((keyPair.getPrivate())) };
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private String convertPublicKeyAsText(PublicKey pubKey) {
        try {
            KeyFactory fact = KeyFactory.getInstance(RSA);
            X509EncodedKeySpec spec = fact.getKeySpec(pubKey, X509EncodedKeySpec.class);
            return new String( Base64.getEncoder().encode(spec.getEncoded()) );
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String convertPrivateKeyAsText(PrivateKey privateKey) {
        try {
            KeyFactory fact = KeyFactory.getInstance(RSA);
            PKCS8EncodedKeySpec spec = fact.getKeySpec(privateKey, PKCS8EncodedKeySpec.class);
            byte[] packed = spec.getEncoded();
            String key64 = new String(Base64.getEncoder().encode(packed));
            Arrays.fill(packed, (byte) 0);
            return key64;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PublicKey convertToPublicKey(String publicKey){
        try {
            byte[] data = Base64.getDecoder().decode(publicKey);
            X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
            KeyFactory fact = KeyFactory.getInstance(RSA);
            return fact.generatePublic(spec);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PrivateKey convertToPrivateKey(String privateKeyStr){
        try{
            byte[] clear = Base64.getDecoder().decode(privateKeyStr);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(clear);
            KeyFactory fact = KeyFactory.getInstance(RSA);
            PrivateKey priv = fact.generatePrivate(keySpec);
            Arrays.fill(clear, (byte) 0);
            return priv;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }



}
