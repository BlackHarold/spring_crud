package ru.bluewhale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("/hello/old")
    public String hello() {
        return "hello-world";
    }
}
