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
import org.springframework.web.bind.annotation.*;

/**
 * @author Jadson Santos - jadsonjs@gmail.com
 */
@RestController
@RequestMapping(path = "/login")
@CrossOrigin
public class LoginRestController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtManager jwt;

    @PostMapping(path = "/signin", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object[] login(@RequestBody AppUser user){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.login, user.password));

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        return new Object[]{userDetails.getAppUser() , jwt.generate(userDetails)};

    }
}


