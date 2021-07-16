/*
 * Cryptography
 * RSASignatureTest
 * @since 16/07/2021
 */
package br.com.jadson.signature;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
class RSASignatureTest {


    /**
     *
     */
    @Test
    void verify() {

        String message = "This is a file content";


        RSASignature rsa = RSASignature.getInstance();
        String[] keys = rsa.generanteKeys();

        byte[] signature = rsa.sign(message.getBytes(), keys[1]);

        System.out.println("public key: "+keys[0]);
        System.out.println("private key: "+keys[1]);

        System.out.println("digital signature: "+ signature);
        System.out.println("digital signature as text: "+ new String(Base64.getEncoder().encode(signature)));

        Assertions.assertTrue(  rsa.verify(message.getBytes(), keys[0], signature));

    }
}