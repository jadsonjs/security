/*
 * Injections
 * LoginController
 * @since 14/07/2021
 */
package br.com.example.controllers;


import br.com.example.model.Login;
import br.com.example.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * A simple example of SQL Injection on Login
 */
@Controller
public class LoginController {

    @Autowired
    UserService userService;

    public ModelAndView login(@ModelAttribute("login") Login login) {
        ModelAndView mv;
        User user = userService.validateLogin(login);
        if (null != user) {
            mv = new ModelAndView("home");
        } else {
            mv = new ModelAndView("login");
            mv.addObject("message", "Username or Password is wrong!!");
        }
        return mv;
    }

}

