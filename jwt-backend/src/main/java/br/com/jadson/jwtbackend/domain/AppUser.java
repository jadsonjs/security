package br.com.jadson.jwtbackend.domain;

import java.util.List;

public class AppUser {

    public Long id;

    public String login;

    public String password;

    public boolean active;

    public List<AppRole> roles;

    public AppUser() {

    }
    public AppUser(long id, String login, String password, boolean active) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.active = active;
    }
}
