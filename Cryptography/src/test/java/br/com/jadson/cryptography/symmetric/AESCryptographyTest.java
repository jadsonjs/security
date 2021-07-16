/*
 * Cryptography
 * AESCryptographyTest
 * @since 16/07/2021
 */
package br.com.jadson.cryptography.symmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
class AESCryptographyTest {


    /**
     *
     */
    @Test
    void encrypt() {

        String message = "This is a secret text";

        AESCryptography aes = AESCryptography.getInstance();
        String key = aes.generateKey();

        byte[] encryptData = aes.encrypt(message, key);

        System.out.println("secret message: "+ message);
        System.out.println("secret key: "+key);
        System.out.println("encrypt data as byte: "+ encryptData);
        System.out.println("encrypt data as text: "+ new String(Base64.getEncoder().encode(encryptData)));

        String messageReceived = aes.decrypt(encryptData, key);
        System.out.println("secret message received: "+ messageReceived);

        Assertions.assertEquals( message, messageReceived);

    }

}