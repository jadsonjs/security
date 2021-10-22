package br.com.jadson.jwtbackend.repositories;

import br.com.jadson.jwtbackend.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class AppUserRepository {

    @Autowired
    public PasswordEncoder passwordEncoder;

    /**
     * In real life: Return a user from the application database
     * @param userName
     * @return
     */
    public AppUser findByLogin(String userName) {
        return new AppUser(1l, "test", passwordEncoder.encode("test"), true);
    }
}
