package ru.naumen.compass.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.naumen.compass.entity.UserAccount;
import ru.naumen.compass.service.RegistrationService;
import ru.naumen.compass.service.SecurityService;
import ru.naumen.compass.validator.UserAccountValidator;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserAccountValidator userAccountValidator;

    @GetMapping("/registration")
    public String showRegistrationForm(Model model){
        model.addAttribute("UserAccount", new UserAccount());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("UserAccount") UserAccount userAccount, BindingResult bindingResult) {
        userAccountValidator.validate(userAccount, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        registrationService.save(userAccount);
        securityService.autoLogin(userAccount.getUsername(), userAccount.getPassword());

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
