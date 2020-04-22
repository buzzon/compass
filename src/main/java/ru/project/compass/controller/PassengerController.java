package ru.project.compass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PassengerController {
    @GetMapping(value = "/passenger/tools")
    public String showCarrierTools(Model model) {
        return "/passenger/tools";
    }
}
