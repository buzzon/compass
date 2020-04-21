package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.project.compass.entity.Stop;
import ru.project.compass.entity.User;
import ru.project.compass.repository.RideRepository;
import ru.project.compass.repository.TransportRepository;
import ru.project.compass.service.SecurityService;

import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private RideRepository rideRepository;

    @GetMapping(value = {"home",""})
    public String showHomePage(Model model){
        model.addAttribute("From", new Stop());
        model.addAttribute("To", new Stop());
        model.addAttribute("DepartureDate", new Date());
        model.addAttribute("ReturnDate", new Date());
        model.addAttribute("Transports", transportRepository.findAll());
        model.addAttribute("Rides", null);

        User user = securityService.getLogged();
        model.addAttribute("User", user);

        return "/home";
    }

    @PostMapping(value = {"home",""})
    public String showHomePageAndRides(Model model){
        model.addAttribute("From", new Stop());
        model.addAttribute("To", new Stop());
        model.addAttribute("DepartureDate", new Date());
        model.addAttribute("ReturnDate", new Date());

        model.addAttribute("Transports", transportRepository.findAll());
        model.addAttribute("Rides", rideRepository.findAll());

        User user = securityService.getLogged();
        model.addAttribute("User", user);

        return "/home";
    }
}
