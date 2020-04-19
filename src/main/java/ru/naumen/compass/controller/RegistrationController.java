package ru.naumen.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumen.compass.entity.Carrier;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.entity.User;
import ru.naumen.compass.service.IRegistrationService;
import ru.naumen.compass.service.ISecurityService;
import ru.naumen.compass.validator.UserValidator;

@Controller
public class RegistrationController {
    @Autowired
    private IRegistrationService IRegistrationService;

    @Autowired
    private ISecurityService ISecurityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration/passenger")
    public String showRegistrationPassengerForm(Model model){
        model.addAttribute("User", new User());
        return "/registration/passenger";
    }

    @GetMapping("/registration/carrier")
    public String showRegistrationCarrierForm(Model model){
        model.addAttribute("User", new User());
        return "/registration/carrier";
    }

    @PostMapping("/registration/passenger")
    public String registrationPassenger(@ModelAttribute("User") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration/passenger";
        }

        Passenger passenger = new Passenger();
        passenger.setF_name(user.getUsername());
        IRegistrationService.save(user, passenger);
        ISecurityService.autoLogin(user.getUsername(), user.getPassword());

        return "greeting";
    }

    @PostMapping("/registration/carrier")
    public String registrationCarrier(@ModelAttribute("User") User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration/carrier";
        }

        Carrier carrier = new Carrier();
        carrier.setTitle(user.getUsername());
        IRegistrationService.save(user, carrier);
        ISecurityService.autoLogin(user.getUsername(), user.getPassword());

        return "greeting";
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
