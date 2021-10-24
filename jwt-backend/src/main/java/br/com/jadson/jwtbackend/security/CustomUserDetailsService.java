package br.com.jadson.jwtbackend.security;

import br.com.jadson.jwtbackend.domain.AppRole;
import br.com.jadson.jwtbackend.domain.AppUser;
import br.com.jadson.jwtbackend.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        AppUser user = userRepository.findByLogin(userName);

        if (user == null) {
            throw new UsernameNotFoundException(userName);
        }else {
            List<GrantedAuthority> authorities = getUserAuthority(user.roles);
            return new CustomUserDetails(new org.springframework.security.core.userdetails.User( user.login, user.password,
                    user.active, true, true, true, authorities), user);
        }
    }

    public List<GrantedAuthority> getUserAuthority(List<AppRole> roles) {
        Set<GrantedAuthority> r = new HashSet<>();
        if(roles != null) {
            for (AppRole p : roles) {
                r.add(new SimpleGrantedAuthority(p.getName()));
            }
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(r);
        return grantedAuthorities;
    }

}
