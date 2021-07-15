/*
 *
 * Injections
 * FooController
 * @since 14/07/2021
 */
package br.com.example.controllers;

import br.com.example.model.Foo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * A example of possible XSS and CSS injection
 */
@Controller
public class FooController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("foo", new Foo());
        return "/index";
    }

    @PostMapping(value = "/submit")
    public String processForm(@ModelAttribute(value="foo") Foo foo, Model model) {
        model.addAttribute("foo", foo);
        return "home";
    }
}
