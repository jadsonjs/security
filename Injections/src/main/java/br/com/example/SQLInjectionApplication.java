/*
 * Universidade Federal do Rio Grande do Norte
 * Instituto Metrópole Digital
 * Diretoria de Tecnologia da Informação
 *
 * sqlinjection
 * SQLInjectionApplication
 * @since 14/07/2021
 */
package br.com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * TODO insert a relevant comment here!
 *
 * @author Jadson Santos - jadson.santos@ufrn.br
 */
@SpringBootApplication
@Configuration
@EnableAutoConfiguration(exclude = {SecurityAutoConfiguration.class})
public class SQLInjectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SQLInjectionApplication.class, args);
    }
}
