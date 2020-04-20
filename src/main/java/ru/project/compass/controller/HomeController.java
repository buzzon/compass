package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.project.compass.entity.Stop;
import ru.project.compass.entity.Transport;
import ru.project.compass.repository.TransportRepository;

import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private TransportRepository transportRepository;

    @GetMapping(value = {"home",""})
    public String showHomePage(Model model){
        model.addAttribute("From", new Stop());
        model.addAttribute("To", new Stop());
        model.addAttribute("DepartureDate", new Date());
        model.addAttribute("ReturnDate", new Date());
        List<Transport> transports = transportRepository.findAll();
        model.addAttribute("Transports", transportRepository.findAll());
        return "/home";
    }

    @GetMapping("greeting")
    public String showGreeting(Model model){
        return "/greeting";
    }
}
