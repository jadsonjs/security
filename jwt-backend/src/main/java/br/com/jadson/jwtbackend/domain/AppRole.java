package br.com.jadson.jwtbackend.domain;

import java.util.Objects;

public class AppRole {

    public static final AppRole ADMIN = new AppRole("ADMIN");
    public static final AppRole COMMON = new AppRole("COMMON") ;
    public static final AppRole GUEST = new AppRole("GUEST") ;

    String name;

    public AppRole(){ }

    public AppRole(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(name, appRole.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
