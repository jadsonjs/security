package br.com.jadson.jwtbackend.domain;

import java.util.Arrays;
import java.util.List;

public class AppUser {

    public Long id;

    public String login;

    public String password;

    public boolean active;

    public List<AppRole> roles;

    public AppUser() {

    }

    /**
     * User full constructor
     * @param id
     * @param login
     * @param password
     * @param active
     * @param roles
     */
    public AppUser(long id, String login, String password, boolean active, AppRole... roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.active = active;
        if(roles != null)
            this.roles = Arrays.asList(roles);
    }
}
