package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.project.compass.entity.Passenger;
import ru.project.compass.repository.PassengerRepository;
import ru.project.compass.service.SecurityService;

@Controller
public class PassengerController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private PassengerRepository passengerRepository;

    @GetMapping(value = "/passenger/tools")
    public String showPassengerTools(Model model) {
        model.addAttribute("Passenger", securityService.getLogged().getPassenger());
        return "/passenger/tools";
    }

    @PostMapping(value = "/passenger/tools", params = "edit")
    public String editPassengerProfile(@ModelAttribute("Passenger") Passenger newData, Model model) {

        Passenger passenger = securityService.getLogged().getPassenger();
        passenger.setF_name(newData.getF_name());
        passenger.setL_name(newData.getL_name());
        passenger.setM_name(newData.getM_name());
        passengerRepository.save(passenger);
        model.addAttribute("Passenger", passenger);
        return "/passenger/tools";
    }
}
