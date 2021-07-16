/*
 * Cryptography
 * RSASignature
 * @since 16/07/2021
 */
package br.com.jadson.signature;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
public final class RSASignature {

    public static final String RSA = "RSA";
    public static final String SHA256withRSA = "SHA256withRSA";

    public static final int KEY_SIZE = 1024;

    private static RSASignature rsa;

    private RSASignature(){ }

    public static RSASignature getInstance(){
        if(rsa == null ){
            rsa = new RSASignature();
        }
        return rsa;
    }

    /**
     * Create a digital signature for a data.
     * @param message
     * @param privateKey
     * @return
     */
    public byte[] sign(byte[] message, String privateKey) {
        try {
            Signature sig = Signature.getInstance(SHA256withRSA);
            sig.initSign(convertToPrivateKey(privateKey));
            sig.update(message);
            byte[] signature = sig.sign();

            return signature;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Check is the digital signature is valid
     *
     * @param mensagem
     * @param pubKey
     * @param signature
     * @return
     */
    public boolean verify(byte[] mensagem, String pubKey, byte[] signature){
        try {
            Signature sig = Signature.getInstance(SHA256withRSA);
            sig.initVerify(convertToPublicKey(pubKey));
            sig.update(mensagem);
            return sig.verify(signature);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Gera as chaves de criptografia RSA
     * @return
     */
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
            return new String(Base64.getEncoder().encode(spec.getEncoded()));
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
            return new String(key64);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }
    }


    private PublicKey convertToPublicKey(String publicKeyStr){
        try {
            byte[] data = Base64.getDecoder().decode(publicKeyStr);
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
