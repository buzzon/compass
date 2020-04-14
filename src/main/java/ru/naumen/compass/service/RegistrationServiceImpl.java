package ru.naumen.compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.repository.PassengerRepository;

import java.util.HashSet;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(Passenger passenger) {
        passenger.setPassword(bCryptPasswordEncoder.encode(passenger.getPassword()));
        passengerRepository.save(passenger);
    }

    @Override
    public Passenger findByUsername(String username) {
        return passengerRepository.findByUsername(username);
    }
}
