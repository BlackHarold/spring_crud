package ru.bluewhale.controller;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(
            @Nullable
            @RequestParam("name")
                    String name) {

        System.out.println(name);
        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodbyePage(HttpServletRequest request) {
        String fName = request.getParameter("fname");
        String sName = request.getParameter("sname");
        System.out.println(fName + " " + sName);
        return "first/goodbye";
    }

    @GetMapping("/model")
    public String getModel(
            @RequestParam(value = "name", required = false) String name,
            Model model) {
        model.addAttribute("key1", "Hello, " + name);
        return "first/model";
    }
}
