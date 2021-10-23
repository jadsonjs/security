package br.com.jadson.jwtbackend.security;

import br.com.jadson.jwtbackend.domain.AppUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SecurityInterceptorFilter implements HandlerInterceptor {

    @Autowired
    JwtManager jwt;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();

        if( ! isOptionsRequest(request) ) {

            if( ! isPublicRequest(request) ) {

                ObjectMapper mapper = new ObjectMapper().registerModule(new ParameterNamesModule()).registerModule(new Jdk8Module()).registerModule(new JavaTimeModule());

                String headerUser = request.getHeader("user");
                String headerAuthorization = request.getHeader("Authorization");

                AppUser user = mapper.readValue(headerUser, AppUser.class);
                String token = headerAuthorization.substring(7);  // Authorization Bearer XXXXXXXXXXXXXXXXXXXX

                if (user == null || ! jwt.validate(token)) {
                    return unauthorized(response, "Expired access");
                }

                restoreApplicationContext(user, request);
                saveUserInSession(user, request);
            }

        }

        return true;
    }



    private boolean isOptionsRequest(HttpServletRequest request) {
        return request.getMethod().equals("OPTIONS");
    }

    
    private boolean isPublicRequest(HttpServletRequest request) {
        if( request.getRequestURI().contains("public") || request.getRequestURI().contains("login")  ){
            return true;
        }
        return false;
    }




    public void restoreApplicationContext(AppUser user, HttpServletRequest request) {

        List< GrantedAuthority > authorities = customUserDetailsService.getUserAuthority(user.roles);
        UserDetails userDetails =  new CustomUserDetails(new org.springframework.security.core.userdetails.User( user.login, user.password,
                user.active, true, true, true, authorities), user);

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

    }

    public void saveUserInSession(AppUser user, HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("user",  user);
    }


    private boolean unauthorized(HttpServletResponse response, String msg) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Allow-Headers", "Content-Length");
            response.setHeader("Access-Control-Allow-Headers", "Date");

            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write(mapper.writeValueAsString(msg));

        } catch (IOException e) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
        return false;
    }


}
