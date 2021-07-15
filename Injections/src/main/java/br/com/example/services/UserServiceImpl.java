/*

 * Injections
 * UserServiceImpl
 * @since 14/07/2021
 */
package br.com.example.services;

import br.com.example.model.Login;
import br.com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public User validateLogin(Login login) {
        return userRepository.validateLogin(login);
    }
}
