/*
 *
 * Injections
 * UserService
 * @since 14/07/2021
 */
package br.com.example.services;

import br.com.example.model.Login;
import org.springframework.security.core.userdetails.User;


public interface UserService {

    User validateLogin(Login login);
}
