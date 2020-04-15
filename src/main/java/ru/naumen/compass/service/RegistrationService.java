package ru.naumen.compass.service;

import ru.naumen.compass.entity.UserAccount;

public interface RegistrationService {
    void save(UserAccount userAccount);
    UserAccount findByUsername(String username);
}
