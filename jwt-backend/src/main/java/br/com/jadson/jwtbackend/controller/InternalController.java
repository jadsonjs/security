package br.com.jadson.jwtbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/internal")
@CrossOrigin
public class InternalController {

    @GetMapping
    public ResponseEntity<String> ping() {
        return new ResponseEntity<>("I AM PROJECTED BY THE TOKEN", HttpStatus.OK);
    }
}
