package de.exxellent.challenge.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/testApplication")
public class TestController {

    @GetMapping(path="")
    public String greet() {
        return "Hello! The application is running just fine. :D";
    }
}
