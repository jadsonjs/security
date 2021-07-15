/*
 *
 * Injections
 * FooController
 * @since 14/07/2021
 */
package br.com.example.controllers;

import br.com.example.model.Data;
import br.com.example.sanitize.JavaSanitize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * A example of possible XSS and CSS injection
 */
@Controller
public class InjectionController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("data", new Data());
        return "/index";
    }

    /**
     * This code allow XSS Injection.
     * @param data
     * @param model
     * @return
     */
    @PostMapping(value = "/submit")
    public String submitWithoutSanitaze(@ModelAttribute(value="foo") Data data, Model model) {
        model.addAttribute("data", data);
        return "home";
    }

    /**
     * This code prevent/avoid XSS Injection
     *
     * @param data
     * @param model
     * @return
     */
    @PostMapping(value = "/sanitaze")
    public String submitWithSanitaze(@ModelAttribute(value="foo") Data data, Model model) {
        data.setUserInput( new JavaSanitize().sanitize(data.getUserInput() ) );
        model.addAttribute("data", data);
        return "home";
    }



}
