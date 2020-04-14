package ru.naumen.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.service.RegistrationService;
import ru.naumen.compass.service.SecurityService;
import ru.naumen.compass.validator.PassengerValidator;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private PassengerValidator passengerValidator;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("passenger", new Passenger());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("passenger") Passenger passengerForm, BindingResult bindingResult) {
        passengerValidator.validate(passengerForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.save(passengerForm);
        securityService.autoLogin(passengerForm.getUsername(), passengerForm.getPassword());

        return "redirect:/greeting";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your login and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }
}
