package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.project.compass.entity.Template;
import ru.project.compass.entity.User;
import ru.project.compass.repository.DirectionRepository;
import ru.project.compass.repository.TemplateRepository;
import ru.project.compass.repository.TransportRepository;
import ru.project.compass.service.SecurityService;

@Controller
public class CarrierController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @GetMapping("/carrier/tools")
    public String showCarrierTools(Model model) {
        model.addAttribute("User", securityService.getLogged());
        model.addAttribute("Template", new Template());
        model.addAttribute("Transports", transportRepository.findAll());
        model.addAttribute("Directions", directionRepository.findAll());
        return "/carrier/tools";
    }

    @PostMapping("/carrier/tools")
    public String AddTemplate(@ModelAttribute("Template") Template template, Model model){
        User user =  securityService.getLogged();
        user.getCarrier().addChildTemplate(template);
        template.setTransport(transportRepository.findById(template.getTransport().getId()).get());
        template.setDirection(directionRepository.findById(template.getDirection().getId()).get());
        templateRepository.save(template);
        
        model.addAttribute("User", user);
        model.addAttribute("Template", new Template());
        model.addAttribute("Transports", transportRepository.findAll());
        model.addAttribute("Directions", directionRepository.findAll());
        return "/carrier/tools";
    }
}
