package ru.project.compass.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.project.compass.entity.Carrier;
import ru.project.compass.entity.Passenger;
import ru.project.compass.entity.Role;
import ru.project.compass.entity.User;
import ru.project.compass.repository.RoleRepository;
import ru.project.compass.repository.UserRepository;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrationService implements IRegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(Collections.singleton(roleRepository.findByTitle("USER")));
        userRepository.save(user);
    }

    @Override
    public void save(User user, Carrier carrier) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByTitle("USER"));
        roles.add(roleRepository.findByTitle("CARRIER"));
        user.setRoles(roles);
        if (carrier.getRating() == null)
            carrier.setRating(4.00f);
        user.setCarrier(carrier);
        userRepository.save(user);
    }

    @Override
    public void save(User user, Passenger passenger) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByTitle("USER"));
        roles.add(roleRepository.findByTitle("PASSENGER"));
        user.setRoles(roles);
        if (passenger.getRating() == null)
            passenger.setRating(4.00f);
        user.setPassenger(passenger);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
