package ru.naumen.compass.service;

import ru.naumen.compass.entity.Passenger;

public interface RegistrationService {
    void save(Passenger passenger);

    Passenger findByUsername(String username);
}
