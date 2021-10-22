package br.com.jadson.jwtbackend.controller;

import br.com.jadson.jwtbackend.domain.AppUser;
import br.com.jadson.jwtbackend.security.CustomUserDetails;
import br.com.jadson.jwtbackend.security.JwtManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jadson Santos - jadsonjs@gmail.com
 */
@RestController
@RequestMapping(path = "/login")
public class LoginRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtManager jwt;

    @PostMapping(path = "/enter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object[] login(@RequestBody AppUser user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.login, user.password));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        String token = jwt.generate(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new Object[]{user , token};

    }
}
