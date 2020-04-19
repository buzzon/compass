package ru.naumen.compass.service;

import ru.naumen.compass.entity.Carrier;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.entity.User;

public interface RegistrationService {
    void save(User user);
    void save(User user, Carrier carrier);
    void save(User user, Passenger passenger);
    User findByUsername(String username);
}
