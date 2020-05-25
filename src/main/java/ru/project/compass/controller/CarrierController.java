package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.project.compass.entity.*;
import ru.project.compass.repository.RideRepository;
import ru.project.compass.repository.StopRepository;
import ru.project.compass.repository.TemplateRepository;
import ru.project.compass.repository.TransportRepository;
import ru.project.compass.service.SecurityService;

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
    private RideRepository rideRepository;

    @Autowired
    private StopRepository stopRepository;

    @ModelAttribute("Transports")
    public List<Transport> transports() {
        return transportRepository.findAll();
    }

    @ModelAttribute("ALLStops")
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

    @PostMapping(value = "/carrier/tools", params = "deleteTemplate")
    public String DeleteTemplate(@RequestParam(name = "templateId") Long id, Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);

        templateRepository.deleteById(id);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "deleteRide")
    public String DeleteRide(@RequestParam(name = "rideId") Long id, Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);

        rideRepository.deleteById(id);

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addRide")
    public String addRide(@RequestParam(name = "templateId") Long id, Model model) {
        model.addAttribute("User", securityService.getLogged());
        Template template =  new Template();
        template.addStop(stops().get(0));
        template.addStop(stops().get(0));
        model.addAttribute("Template", template);

        if (templateRepository.findById(id).isPresent()){
            Template template1 = templateRepository.findById(id).get();

            // добавить рейс по шаблону
		    Ride ride = new Ride();
			ride.setValid(true);
            template1.addChildrenRide(ride);
			rideRepository.save(ride);
        }

        return "/carrier/tools";
    }

    @PostMapping(value = "/carrier/tools", params = "addTemplate")
    public String AddTemplate(@ModelAttribute("Template") Template template, Model model) {
        User user = securityService.getLogged();
        user.getCarrier().addChildTemplate(template);
        if (transportRepository.findById(template.getTransport().getId()).isPresent())
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
