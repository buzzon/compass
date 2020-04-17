package ru.naumen.compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.naumen.compass.entity.Carrier;
import ru.naumen.compass.entity.Passenger;
import ru.naumen.compass.entity.User;
import ru.naumen.compass.repository.RoleRepository;
import ru.naumen.compass.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;

@Service
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user, Passenger passenger) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByTitle("USER")));
        user.setPassenger(passenger);
        userRepository.save(user);
    }

    @Override
    public void save(User user, Carrier carrier) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByTitle("USER")));
        user.setCarrier(carrier);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
