/*
 *
 * Cryptography
 * RSACryptographyTest
 * @since 16/07/2021
 */
package br.com.jadson.cryptography.asymmetric;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO insert a relevant comment here!
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
class RSACryptographyTest {


    /**
     *
     */
    @Test
    void encrypt() {

        String message = "This is a secret text";

        RSACryptography rsa = RSACryptography.getInstance();
        String[] keys = rsa.generanteKeys();

        byte[] encryptData = rsa.encrypt(message, keys[0]);

        System.out.println("public key: "+keys[0]);
        System.out.println("private key: "+keys[1]);

        System.out.println("encrypt message as byte: "+ encryptData);
        System.out.println("encrypt message as text: "+ new String(Base64.getEncoder().encode(encryptData)));
        String messageReceived = rsa.decrypt(encryptData, keys[1]);

        System.out.println("secret message received: "+ messageReceived);

        Assertions.assertEquals( message, messageReceived);

    }
}