package ru.project.compass.service;

import ru.project.compass.entity.Carrier;
import ru.project.compass.entity.Passenger;
import ru.project.compass.entity.User;

public interface IRegistrationService {
    void save(User user);
    void save(User user, Carrier carrier);
    void save(User user, Passenger passenger);
    User findByUsername(String username);
}
