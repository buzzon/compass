package ru.project.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.project.compass.entity.Carrier;
import ru.project.compass.entity.Passenger;
import ru.project.compass.entity.User;
import ru.project.compass.validator.UserValidator;

@Controller
public class RegistrationController {
    @Autowired
    private ru.project.compass.service.IRegistrationService IRegistrationService;

    @Autowired
    private ru.project.compass.service.ISecurityService ISecurityService;

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
    public String registrationPassenger(@ModelAttribute("User") User user, BindingResult bindingResult){
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration/passenger";
        }

        Passenger passenger = new Passenger();
        passenger.setF_name(user.getUsername());
        IRegistrationService.save(user, passenger);
        ISecurityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/";
    }

    @PostMapping("/registration/carrier")
    public String registrationCarrier(@ModelAttribute("User") User user, BindingResult bindingResult, Model model){
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration/carrier";
        }

        Carrier carrier = new Carrier();
        carrier.setTitle(user.getUsername());
        IRegistrationService.save(user, carrier);
        ISecurityService.autoLogin(user.getUsername(), user.getPassword());

        return "redirect:/";
    }
}