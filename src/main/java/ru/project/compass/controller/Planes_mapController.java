package ru.project.compass.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Planes_mapController {

    @GetMapping(value = {"planes_map"})
    public String showHomePage(Model model){
        return "/planes_map";
    }
}
