/*
 * Injections
 * SQLInjectionApplication
 * @since 14/07/2021
 */
package br.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class JavaInjectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaInjectionApplication.class, args);
    }
}
