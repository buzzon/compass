package ru.naumen.compass.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.naumen.compass.entity.UserAccount;
import ru.naumen.compass.service.RegistrationService;

@Component
public class UserAccountValidator implements Validator {

    @Autowired
    private RegistrationService registrationService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserAccount.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserAccount userAccount = (UserAccount) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (userAccount.getUsername().length() < 4 || userAccount.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
        if (registrationService.findByUsername(userAccount.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (userAccount.getPassword().length() < 8 || userAccount.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }
    }
}
