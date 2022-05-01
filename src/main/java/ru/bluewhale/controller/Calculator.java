package ru.bluewhale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Calculator {

    @GetMapping("/calculator")
    public String calculate(@RequestParam("first") String a,
                            @RequestParam("second") String b,
                            @RequestParam("operation") String operation,
                            Model model) {

        String operation1 = (String) model.getAttribute("operation");
        System.out.println("calc " + operation1);

        model.addAttribute("first_num", a);
        model.addAttribute("second_num", b);
        model.addAttribute("operation", operation);

        //count result
        float aToFloat = Float.parseFloat(a);
        float bToFloat = Float.parseFloat(b);
        System.out.println(aToFloat + " " + bToFloat);
        String result;
        switch (operation) {
            case "addition" -> result = String.valueOf(aToFloat + bToFloat);
            case "subtraction" -> result = String.valueOf(aToFloat - bToFloat);
            case "multiplication" -> result = String.valueOf(aToFloat * bToFloat);
            case "divide" -> {
                if (bToFloat != 0) {
                    result = String.valueOf(aToFloat / bToFloat);
                } else {
                    result = "Can't divide by zero";
                }
            }

            default -> result = ("Unexpected value: " + operation);
        }

        model.addAttribute("result", result);
        return "calculator/calculator";
    }
}
