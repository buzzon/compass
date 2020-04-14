package ru.naumen.compass.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.service.RegistrationService;

@Component
public class PassengerValidator implements Validator {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return Passenger.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Passenger passenger = (Passenger) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (passenger.getUsername().length() < 4 || passenger.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (registrationService.findByUsername(passenger.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (passenger.getPassword().length() < 8 || passenger.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
