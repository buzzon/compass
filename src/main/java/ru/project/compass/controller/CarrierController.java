package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.project.compass.entity.Stop;
import ru.project.compass.entity.Template;
import ru.project.compass.entity.Transport;
import ru.project.compass.entity.User;
import ru.project.compass.repository.StopRepository;
import ru.project.compass.repository.TemplateRepository;
import ru.project.compass.repository.TransportRepository;
import ru.project.compass.service.SecurityService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarrierController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private TransportRepository transportRepository;

    @Autowired
    private StopRepository stopRepository;

    @ModelAttribute("Transports")
    public List<Transport> transports() {
        return transportRepository.findAll();
    }

    @ModelAttribute("Stops")
    public List<Stop> stops() {
        return stopRepository.findAll();
    }

    @GetMapping(value = "/carrier/tools")
    public String showCarrierTools(Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);
        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addTemplate")
    public String AddTemplate(@ModelAttribute("Template") Template template, Model model) {
        User user = securityService.getLogged();
        user.getCarrier().addChildTemplate(template);
        template.setTransport(transportRepository.findById(template.getTransport().getId()).get());
        templateRepository.save(template);

        model.addAttribute("User", user);
        model.addAttribute("Template", template);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addStop")
    public String showCarrierAddStop(@ModelAttribute("Template") Template template, Model model) {
        model.addAttribute("User", securityService.getLogged());
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);
        return "/carrier/tools";
    }
}
